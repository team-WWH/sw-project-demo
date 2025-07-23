package com.example.wwh.Controller;


import com.example.wwh.pojo.Feedback;
import com.example.wwh.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.messaging.handler.annotation.MessageMapping;
import com.example.wwh.pojo.Feedback;
import com.example.wwh.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.List;


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

    public FeedbackController(SimpMessagingTemplate messagingTemplate, FeedbackService feedbackService) {
        this.messagingTemplate = messagingTemplate;
        this.feedbackService = feedbackService;
    }

    // 获取与特定 SpeechID 和 ListenerID 相关的所有历史消息
    @GetMapping("/history")
    public List<Feedback> getMessages(@RequestParam int speechID) {
        return feedbackService.getMessagesBySpeechAndListener(speechID);
    }

    // 实时反馈，处理 WebSocket 消息
    @MessageMapping("/chat")
    public void sendChatMessage(Feedback message) {
        // 保存消息到数据库
        feedbackService.submitFeedback(message);

        // 获取所有与 SpeechID 相关的 ListenerID
        //List<Integer> listeners = feedbackService.getListenersBySpeechID(message.getSpeechID());

        // 2. 修改为：广播到特定演讲室的主题
        String topic = "/topic/chat/" + message.getSpeechID();
        messagingTemplate.convertAndSend(topic, message);
    }
}
