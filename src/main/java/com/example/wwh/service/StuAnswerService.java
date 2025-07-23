package com.example.wwh.service;

import com.example.wwh.Mapper.StuAnswerMapper;
import com.example.wwh.pojo.Stuanswers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StuAnswerService {
    @Autowired
    private StuAnswerMapper stuanswersMapper;



    // 根据 ListenerID 和 QuestionID 获取答案数据(听众进入某个题目要展示)
    public Stuanswers getAnswerByListenerAndQuestion(int listenerID, int questionID) {
        return stuanswersMapper.findAnswerByListenerAndQuestion(listenerID, questionID);
    }

    public void addStuAnswer(Stuanswers stuanswers){
        stuanswersMapper.addstuAnswer(stuanswers);
    }



}
