package com.example.wwh.Controller;

import com.example.wwh.service.AIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;

import static ch.qos.logback.core.joran.JoranConstants.NULL;

@Controller
public class AIController {
    @Autowired
    AIService aiservice;
    String apiUrl = "https://open.bigmodel.cn/api/paas/v4/chat/completions";
//    @PutMapping
//    public String UseAI(){
//    }
}
