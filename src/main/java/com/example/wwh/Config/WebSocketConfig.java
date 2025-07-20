package com.example.wwh.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker // 关键变更：启用STOMP消息代理
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 注册STOMP端点
        registry.addEndpoint("/listener/tofeedback") // 保持你的原始端点
                .setAllowedOriginPatterns("*")  // 允许跨域
                .withSockJS();  // 可选：支持SockJS
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 启用简单内存消息代理
        registry.enableSimpleBroker("/topic", "/queue");

        // 设置应用目标前缀（客户端发送消息的前缀）
        registry.setApplicationDestinationPrefixes("/app");
    }
}