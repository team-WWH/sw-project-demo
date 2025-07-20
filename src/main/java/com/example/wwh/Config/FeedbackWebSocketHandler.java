package com.example.wwh.Config;

import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class FeedbackWebSocketHandler extends TextWebSocketHandler {

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        // 处理接收到的消息
        System.out.println("Received message: " + message.getPayload());

        // 发送响应消息
        try {
            session.sendMessage(new TextMessage("Feedback received"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
