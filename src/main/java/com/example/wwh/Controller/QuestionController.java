package com.example.wwh.Controller;



import com.example.wwh.DTO.QuestionDTO;

import com.example.wwh.Data.StuanwersResponse;

import com.example.wwh.pojo.Question;
import com.example.wwh.pojo.Comment;
import com.example.wwh.pojo.Stuanswers;
import com.example.wwh.service.*;
import org.apache.ibatis.annotations.Param;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/Comment/questions")
@EnableAsync
public class QuestionController {

    @Autowired
    private Pdfparser pdfParser;
    //@Autowired private PptParser pptParser;
    @Autowired private AIService aiService;
    @Autowired
    private PPTParser pptParser;
    private final Executor questionExecutor = Executors.newFixedThreadPool(5);
    @Autowired
    private FileSplitService fileSplitService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private  SimpMessagingTemplate messagingTemplate;


    private final String[] API_ENDPOINTS = {
            "https://open.bigmodel.cn/api/paas/v4/chat/completions",

    };

    private final String[] API_KEYS = {
            "79f03a71e4dd418fb376f1ccb85819f9.b8KKhydUu7rUz1P3",
            "dcb58333b1d244dabc0199ac0da0d70d.nPuTCTpIZdT6XjkT",
            "899b4ff0a9a34e988da07180d2265979.cng4pdsEMlVWqqoe",
            "fe63429dedc942dbb3ef94037de4a5af.0OcuWOpTGYGK3wvq",
            "b8e68ae4d23d4a2e811313fb65c47e56.xmmRYfUBmsl8YbON",
    };
    @Autowired
    private MinioService minioService;
    @Autowired
    private StuAnswerService stuAnswerService;
    //调用ai生成题目
    @GetMapping("/generate")
    public ResponseEntity<?> generateQuestions(@Param("currentNumber") Integer currentNumber,
                                               @Param("filename") String filename,
                                               @Param("SpeechID") Integer SpeechID) {
        String name = filename;
        try {
            List<String> urls = fileSplitService.splitFile(name, currentNumber);
            Integer page = 0;
            if(name.endsWith(".pdf")){
                page = 3;
            }
            else{
                page = 5;
            }
            Integer length = 0;
            if(currentNumber%page == 0){
                length =  currentNumber/page - 1;
            }
            else{
                length =  currentNumber/page;
            }
           // System.out.println("查看一下长度：******"+length);
            List<CompletableFuture<List<Question>>> futures = new ArrayList<>();
            Random random = new Random();

            for (int i = 0; i < 5; i++) {
                int finalLength = length;
                CompletableFuture<List<Question>> future = CompletableFuture.supplyAsync(() -> {
                    try {
                        int num = ThreadLocalRandom.current().nextInt(finalLength);
                        System.out.println("选择的随机分片: " + urls.get(num));
                        byte[] fileData = minioService.downloadFile(urls.get(num));
                        String content;
                        if (name.endsWith(".pdf")) {
                            try (PDDocument document = Loader.loadPDF(fileData)) {
                                content = pdfParser.parse(document);
                            }
                        } else if (name.endsWith(".ppt") || name.endsWith(".pptx")) {
                            content = pptParser.parse(fileData, name);
                        } else {
                            throw new IllegalArgumentException("不支持的文件格式");
                        }
                        int index = ThreadLocalRandom.current().nextInt(API_KEYS.length);
                        String endpoint = API_ENDPOINTS[0];
                        String apiKey = API_KEYS[index];
                        Question[] questions = aiService.generateQuestions(content, endpoint, apiKey);
                        return Arrays.asList(questions);
                    } catch (Exception e) {
                        System.err.println("任务执行失败: " + e.getMessage());
                        return Collections.emptyList();
                    }
                }, questionExecutor);

                futures.add(future);
            }

            CompletableFuture<Void> allFutures = CompletableFuture.allOf(
                    futures.toArray(new CompletableFuture[0])
            );

            CompletableFuture<List<Question>> combined = allFutures.thenApply(v ->
                    futures.stream()
                            .flatMap(future -> future.join().stream())
                            .collect(Collectors.toList())
            );

            List<Question> questionsList = combined.get(120, TimeUnit.SECONDS);

            for (Question question : questionsList) {
                questionService.addQuestion(question);
                System.out.println("这里是questionid"+question.getQuestionID());
            }
            for (Question question : questionsList) {
            questionService.addqueconspe(SpeechID,question.getQuestionID());
            }
            String topic = "/topic/chat/" + SpeechID;
            messagingTemplate.convertAndSend(topic, "QUESTION_PUBLISH");
            return ResponseEntity.ok(questionsList);

        } catch (InterruptedException | ExecutionException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("并发处理错误: " + e.getMessage());
        } catch (TimeoutException e) {
            return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT)
                    .body("请求超时: 120秒内未完成所有题目生成");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("处理失败: " + e.getMessage());
        }
    }
    //获得现在的刚刚发布的题目
    @GetMapping("/getnowquestion")
    public ResponseEntity<String> getnowQuestion(@Param("SpeechID") Integer SpeechID,
                                                 @Param("ListenerID") Integer ListenerID){
        List<Question>  questions =  questionService.getAllQuestionStatus1(SpeechID);
        List<Stuanswers> stuanswersList = new ArrayList<>();
        for(Question question:questions){
            Stuanswers stuanswers = new Stuanswers();
            stuanswers.setQuestionID(question.getQuestionID());
            stuanswers.setListenerID(ListenerID);
            stuanswers.setQS(0);
//            Integer i = 0;
//            if(question.getAnswer()=="A"){
//                i = 1;
//            }else if(question.getAnswer()=="B"){
//                i = 2;
//            }else if(question.getAnswer()=="C"){
//                i = 3;
//            }else if(question.getAnswer()=="D"){
//                i = 4;
//            }
            stuanswers.setSanscontent("0");
            stuanswers.setState(0);
            stuanswersList.add(stuanswers);
        }
        Random random = new Random();
        stuanswersList.get(random.nextInt(stuanswersList.size())).setQS(1);
       for(Stuanswers stuanswers:stuanswersList){
           stuAnswerService.addStuAnswer(stuanswers);
       }

       return ResponseEntity.ok("题目获取成功");
    }


    //进入每个题目详情的时候，可以让前端之间先把这个题目的Question内容传过去，然后下个页面提取comment和stuAnswer即可

    //获取个人的进行中的题目
    @GetMapping("/listener/getOpeningQuestions")
    public ResponseEntity<List<QuestionDTO>> getQuestions(@RequestParam int speechID, @RequestParam int listenerID) {
        List<QuestionDTO> questions = questionService.getQuestions(speechID, listenerID);
        return ResponseEntity.ok(questions);
    }

    // 获取已经结束的题目
    @GetMapping("/listener/getfinishedQuestions")
    public ResponseEntity<List<QuestionDTO>> getFinishedQuestions(
            @RequestParam int speechID,
            @RequestParam int listenerID) {

        // 调用 service 层获取题目
        List<QuestionDTO> questions = questionService.getFinishedQuestions(speechID, listenerID);

        return ResponseEntity.ok(questions);  // 返回题目数据
    }

    // 获取没抽到的题目
    @GetMapping("/listener/getNogetQuestions")
    public ResponseEntity<List<QuestionDTO>> getNogetQuestions(
            @RequestParam int speechID,
            @RequestParam int listenerID) {

        // 调用 service 层获取题目
        List<QuestionDTO> questions = questionService.getNogetQuestions(speechID, listenerID);

        return ResponseEntity.ok(questions);  // 返回题目数据
    }

    // 获取某个题目的评论
    @GetMapping("/listener/getcomments")
    public ResponseEntity<List<Comment>> getCommentsForQuestion(
            @RequestParam int questionID) {

        // 调用 service 层获取评论
        List<Comment> comments = questionService.getCommentsForQuestion(questionID);

        return ResponseEntity.ok(comments);  // 返回评论数据
    }

    // 获取某个听众收藏的题目
    @GetMapping("/listener/collect")
    public ResponseEntity<List<QuestionDTO>> getQuestionsByListener(
            @RequestParam int listenerID) {

        List<QuestionDTO> questions = questionService.getQuestionsByListenerAndStatus(listenerID);
        System.out.println(questions);
        return ResponseEntity.ok(questions);
    }


    // 收藏题目
    @PostMapping("listener/addtocollect")
    public ResponseEntity<String> addToCollect(@RequestParam int listenerID, @RequestParam int questionID) {
        questionService.addToCollect(listenerID, questionID);
        return ResponseEntity.ok("题目已收藏");
    }


    // 根据 QuestionID 和 ListenerID 查询是否已收藏
    @GetMapping("listener/checktocollect")
    public ResponseEntity<String> checkIfCollected(@RequestParam int questionID, @RequestParam int listenerID) {
        boolean isCollected = questionService.isAlreadyCollected(questionID, listenerID);
        if (isCollected) {
            return ResponseEntity.ok("The question has already been collected.");
        } else {
            return ResponseEntity.ok("The question has not been collected yet.");
        }
    }


    //获取演讲的进行中的题目
    @GetMapping("/speaker/getSpeechOpeningQuestions")
    public ResponseEntity<List<Question>> getSpeechOnQuestions(@RequestParam int speechID) {
        List<Question> questions = questionService.getSpeechOnQuestions(speechID);
        return ResponseEntity.ok(questions);
    }

    //获取演讲的已结束的题目
    @GetMapping("/speaker/getSpeechEndQuestions")
    public ResponseEntity<List<Question>> getSpeechEndQuestions(@RequestParam int speechID) {
        List<Question> questions = questionService.getSpeechEndQuestions(speechID);
        return ResponseEntity.ok(questions);
    }



    // 插入评论的接口
    @PostMapping("/listener/addComment")
    public String addComment(@RequestParam int listenerID,
                             @RequestParam int questionID,
                             @RequestParam String comcontent) {
        questionService.addComment(listenerID, questionID, comcontent);
        return "Comment added successfully!";
    }

//更改题目状态
    @GetMapping("/updateStatus")
    public String updateStatus(@RequestParam("QuestionID") int questionID) {
        System.out.println(questionID);
        questionService.updateQstatus(questionID);
        return "更新成功";
    }


}
