package com.example.wwh.Controller;


import com.example.wwh.pojo.Feedback;
import com.example.wwh.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.messaging.handler.annotation.MessageMapping;
import com.example.wwh.pojo.Feedback;
import com.example.wwh.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;


@Controller
@RestController
@RequestMapping("/feedback")
public class FeedbackController {
//    @Autowired
//    private FeedbackService feedbackService;

    // 提交反馈的 API
//    @PostMapping("/listener/")
//    public ResponseEntity<String> submitFeedback(@RequestBody Feedback feedback) {
//        // 调用 Service 层处理业务逻辑
//        String responseMessage = feedbackService.submitFeedback(feedback);
//        if (responseMessage.contains("成功")) {
//            return ResponseEntity.ok(responseMessage);
//        } else {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
//        }
//    }


    //实时反馈
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    private FeedbackService feedbackService;

    public FeedbackController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/chat")
    public void sendChatMessage(Feedback message) {
        System.out.println(message.getFcontent());
        // 保存消息到数据库
        feedbackService.submitFeedback(message);
        // 广播消息到所有 WebSocket 客户端
        messagingTemplate.convertAndSend("/topic/chat", message);
    }
}
