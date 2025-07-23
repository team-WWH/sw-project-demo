package com.example.wwh.service;


import com.example.wwh.Mapper.FeedbackMapper;
import com.example.wwh.pojo.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackMapper feedbackMapper;

    // 获取与特定 SpeechID 相关的所有 Listener
    public List<Integer> getListenersBySpeechID(int speechID) {
        return feedbackMapper.getListenersBySpeechID(speechID);
    }

    // 提交反馈并保存消息
    public void submitFeedback(Feedback feedback) {
        feedbackMapper.submitFeedback(feedback);
    }

    // 获取与特定 SpeechID 和 ListenerID 相关的所有历史消息
    public List<Feedback> getMessagesBySpeechAndListener(int speechID) {
        return feedbackMapper.getMessagesBySpeechAndListener(speechID);
    }
}
