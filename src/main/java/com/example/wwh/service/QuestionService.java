package com.example.wwh.service;


import com.example.wwh.Mapper.QuestionMapper;
import com.example.wwh.pojo.Comment;
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

    // 获取已经结束的题目
    public List<Question> getFinishedQuestions(int speechID, int listenerID) {
        return questionMapper.findQuestionsBySpeechAndListener2(speechID, listenerID);
    }

    // 获取没抽到的题目
    public List<Question> getNogetQuestions(int speechID, int listenerID) {
        return questionMapper.findQuestionsBySpeechAndListener3(speechID, listenerID);
    }

    // 获取某个题目的评论
    public List<Comment> getCommentsForQuestion(int questionID) {
        return questionMapper.findCommentsByQuestionID(questionID);
    }

    // 获取某个听众收藏的题目
    public List<Question> getQuestionsByListenerAndStatus(int listenerID) {
        return questionMapper.findQuestionsByListenerAndStatus(listenerID);
    }

    //听众进行题目收藏
    // 添加收藏
    public void addToCollect(int listenerID, int questionID) {
        questionMapper.addToCollect(listenerID, questionID);
    }


}
