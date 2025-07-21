package com.example.wwh.Controller;


import com.example.wwh.pojo.Question;
import com.example.wwh.pojo.Comment;
import com.example.wwh.service.AIService;
import com.example.wwh.service.Pdfparser;
import com.example.wwh.service.QuestionService;
import org.apache.pdfbox.Loader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


@RestController
@RequestMapping("/api/questions")
@EnableAsync
public class QuestionController {

    @Autowired
    private Pdfparser pdfParser;
    //@Autowired private PptParser pptParser;
    @Autowired private AIService aiService;


    private final Executor questionExecutor = Executors.newFixedThreadPool(1);

    @Autowired
    private QuestionService questionService;



    private final String[] API_ENDPOINTS = {
            "https://open.bigmodel.cn/api/paas/v4/chat/completions",

    };

    private final String[] API_KEYS = {
            "79f03a71e4dd418fb376f1ccb85819f9.b8KKhydUu7rUz1P3",

    };

    @GetMapping("/generate")
    public ResponseEntity<?> generateQuestions() throws IOException {
        File file = new File("C://Users//王镜然//Desktop//任务1代码说明文档.pdf");
        try {
            String content = "";
            String filename = "任务1代码说明文档.pdf";

            if (filename.endsWith(".pdf")) {
                content = pdfParser.parse( file);
            } else if (filename.endsWith(".ppt") || filename.endsWith(".pptx")) {
               // content = pptParser.parse(file);
            } else {
                return ResponseEntity.badRequest().body("不支持的文件格式");
            }

            List<Question> questionsList = new ArrayList<>();
            for (int i = 0; i < 1; i++) {

                String endpoint = API_ENDPOINTS[i % API_ENDPOINTS.length];
                String apiKey = API_KEYS[i % API_KEYS.length];


                questionsList.add(aiService.generateQuestions(content, endpoint, apiKey));
            }
           // String apiUrl = "https://open.bigmodel.cn/api/paas/v4/chat/completions";
            //String apiKey = "79f03a71e4dd418fb376f1ccb85819f9.b8KKhydUu7rUz1P3";
           // String questions = aiService.generateQuestions(content, apiUrl, apiKey);
            return ResponseEntity.ok(questionsList);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("处理失败: " + e.getMessage());
        }
    }


    //进入每个题目详情的时候，可以让前端之间先把这个题目的Question内容传过去，然后下个页面提取comment和stuAnswer即可

    //获取个人的进行中的题目
    @GetMapping("/listener/getOpeningQuestions")
    public ResponseEntity<List<Question>> getQuestions(@RequestParam int speechID, @RequestParam int listenerID) {
        List<Question> questions = questionService.getQuestions(speechID, listenerID);
        return ResponseEntity.ok(questions);
    }

    // 获取已经结束的题目(未测试)
    @GetMapping("/listener/getfinishedQuestions")
    public ResponseEntity<List<Question>> getFinishedQuestions(
            @RequestParam int speechID,
            @RequestParam int listenerID) {

        // 调用 service 层获取题目
        List<Question> questions = questionService.getFinishedQuestions(speechID, listenerID);

        return ResponseEntity.ok(questions);  // 返回题目数据
    }

    // 获取没抽到的题目(未测试)
    @GetMapping("/listener/getNogetQuestions")
    public ResponseEntity<List<Question>> getNogetQuestions(
            @RequestParam int speechID,
            @RequestParam int listenerID) {

        // 调用 service 层获取题目
        List<Question> questions = questionService.getNogetQuestions(speechID, listenerID);

        return ResponseEntity.ok(questions);  // 返回题目数据
    }

    // 获取某个题目的评论（未测试）
    @GetMapping("/listener/getcomments")
    public ResponseEntity<List<Comment>> getCommentsForQuestion(
            @RequestParam int questionID) {

        // 调用 service 层获取评论
        List<Comment> comments = questionService.getCommentsForQuestion(questionID);

        return ResponseEntity.ok(comments);  // 返回评论数据
    }

    // 获取某个听众收藏的题目（未测试）
    @GetMapping("/listener/collect")
    public ResponseEntity<List<Question>> getQuestionsByListener(
            @RequestParam int listenerID) {

        List<Question> questions = questionService.getQuestionsByListenerAndStatus(listenerID);
        return ResponseEntity.ok(questions);
    }


    // 收藏题目（未测试）
    @PostMapping("listener/addtocollect")
    public ResponseEntity<String> addToCollect(@RequestParam int listenerID, @RequestParam int questionID) {
        questionService.addToCollect(listenerID, questionID);
        return ResponseEntity.ok("题目已收藏");
    }


    // 根据 QuestionID 和 ListenerID 查询是否已收藏（未测试）
    @GetMapping("listener/checktocollect")
    public ResponseEntity<String> checkIfCollected(@RequestParam int questionID, @RequestParam int listenerID) {
        boolean isCollected = questionService.isAlreadyCollected(questionID, listenerID);
        if (isCollected) {
            return ResponseEntity.ok("The question has already been collected.");
        } else {
            return ResponseEntity.ok("The question has not been collected yet.");
        }
    }

}
