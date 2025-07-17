package com.example.wwh.service;


import com.example.wwh.Mapper.FeedbackMapper;
import com.example.wwh.pojo.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackMapper feedbackMapper;

    // 提交反馈
    public String submitFeedback(Feedback feedback) {
        try {
            feedbackMapper.submitFeedback(feedback);
            return "成功提交";
        } catch (Exception e) {
            e.printStackTrace();
            return "提交失败";
        }
    }
}
