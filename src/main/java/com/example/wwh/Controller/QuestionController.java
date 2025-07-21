package com.example.wwh.Controller;

import com.example.wwh.pojo.Question;
import com.example.wwh.service.AIService;
import com.example.wwh.service.Pdfparser;
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
}
