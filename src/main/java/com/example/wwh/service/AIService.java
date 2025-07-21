package com.example.wwh.service;

import com.example.wwh.pojo.Question;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AIService {
    private final RestTemplate restTemplate = new RestTemplate();

    public Question generateQuestions(String content, String apiUrl, String apiKey) throws JsonProcessingException {
        // 构造请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);

        // 构造提示词
        String prompt = String.format("""
            你是一位资深教师，请根据以下教学内容生成1道题目：
            【教学材料】
            %s
            
            【要求】
            1. 包含1道包含四个选项的选择题
            2. 标注答案和解析
            3. 难度适中，考察核心知识点
            4.严格按照以下josn返回，不要包含任何额外文本、注释或Markdown代码块，同时大小写也要与下列示例对应：
            {
                "Questioncontent":"题目内容",
                "A":"选项A",
                "B":"选项B",
                "C":"选项C",
                "D":"选项D",
                "Answer":"答案",
                "Analysis":"解析"
            }
            """, content.substring(0, Math.min(content.length(), 6000)));

        // 构造请求体
        Map<String, Object> request = new HashMap<>();
        request.put("model", "glm-4-plus");
        request.put("messages", List.of(Map.of("role", "user", "content", prompt)));
        request.put("temperature", 0.3);
        request.put("max_tokens", 1500);

        // 发送请求
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, headers);
        ResponseEntity<Map> response = restTemplate.exchange(
                apiUrl, HttpMethod.POST, entity, Map.class);
        ObjectMapper mapper = new ObjectMapper();
        // 解析响应
        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            Map<String, Object> choices = ((List<Map<String, Object>>)
                    response.getBody().get("choices")).get(0);
            String contents =  (String) ((Map<String, Object>) choices.get("message")).get("content");
            System.out.println("返回的josn内容："+contents);
            Question question = mapper.readValue(contents, Question.class);
            return question;
        }
        throw new RuntimeException("API调用失败: " + response.getStatusCode());
    }
}
