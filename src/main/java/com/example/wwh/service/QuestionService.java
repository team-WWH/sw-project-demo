package com.example.wwh.service;


import com.example.wwh.Mapper.QuestionMapper;
import com.example.wwh.pojo.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service

public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;


    //获取听众个人的进行中的题目
    public List<Question> getQuestions(int speechID, int listenerID) {
        return questionMapper.findQuestionsBySpeechAndListener1(speechID, listenerID);
    }
}
