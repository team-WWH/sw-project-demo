package com.example.wwh.Controller;

import com.example.wwh.service.AIService;
import com.example.wwh.service.Pdfparser;
import org.apache.pdfbox.Loader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    @Autowired
    private Pdfparser pdfParser;
    //@Autowired private PptParser pptParser;
    @Autowired private AIService aiService;



    @GetMapping("/generate")
    public ResponseEntity<String> generateQuestions() throws IOException {
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

            // 调用AI生成题目
            String apiUrl = "https://open.bigmodel.cn/api/paas/v4/chat/completions";
            String apiKey = "79f03a71e4dd418fb376f1ccb85819f9.b8KKhydUu7rUz1P3";
            String questions = aiService.generateQuestions(content, apiUrl, apiKey);
            return ResponseEntity.ok(questions);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("处理失败: " + e.getMessage());
        }
    }
}
