package com.example.wwh.service;


import com.example.wwh.DTO.QuestionDTO;
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

    // 获取听众个人已经结束的题目
    public List<QuestionDTO> getFinishedQuestions(int speechID, int listenerID) {
        return questionMapper.findQuestionsBySpeechAndListener2(speechID, listenerID);
    }

    // 获取听众个人没抽到的题目
    public List<QuestionDTO> getNogetQuestions(int speechID, int listenerID) {
        return questionMapper.findQuestionsBySpeechAndListener3(speechID, listenerID);
    }

    // 获取某个题目的评论
    public List<Comment> getCommentsForQuestion(int questionID) {
        return questionMapper.findCommentsByQuestionID(questionID);
    }

    // 获取某个听众收藏的题目
    public List<QuestionDTO> getQuestionsByListenerAndStatus(int listenerID) {
        return questionMapper.findQuestionsByListenerAndStatus(listenerID);
    }

    //听众进行题目收藏
    // 添加收藏
    public void addToCollect(int listenerID, int questionID) {
        questionMapper.addToCollect(listenerID, questionID);
    }


    // 根据 QuestionID 和 ListenerID 查询是否已收藏
    public boolean isAlreadyCollected(int questionID, int listenerID) {
        int count = questionMapper.countCollectByQuestionAndListener(questionID, listenerID);
        return count > 0;  // 如果返回值大于 0，则表示该题目已经被收藏
    }


    //获取演讲的进行中的题目
    public List<Question> getSpeechOnQuestions(int speechID) {
        return questionMapper.findQuestionsBySpeechAndListener4(speechID);
    }

    //获取演讲的已结束的题目
    public List<Question> getSpeechEndQuestions(int speechID) {
        return questionMapper.findQuestionsBySpeechAndListener5(speechID);
    }

    // 插入评论
    public void addComment(int listenerID, int questionID, String comcontent) {
        questionMapper.insertComment(listenerID, questionID, comcontent);
    }
        public void addQuestion (Question question){
            questionMapper.addquestion(question);
        }

        public List<Question> getAllQuestionStatus1 (Integer SpeechID){
            return questionMapper.getOningQuestionBySpeechID(SpeechID);
        }

        public void addqueconspe (Integer SpeechID, Integer QuestionID){
            questionMapper.addqueconspe(SpeechID, QuestionID);

        }
    }
