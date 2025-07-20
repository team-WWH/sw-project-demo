package com.example.wwh.Mapper;


import com.example.wwh.DTO.QuestionDTO;
import com.example.wwh.pojo.Comment;
import com.example.wwh.pojo.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionMapper {


    //听众个人的进行中的题目
    @Select("SELECT q.* FROM Question q " +
            "JOIN QueconSpe qs ON q.QuestionID = qs.QuestionID " +
            "JOIN Stuanswers sa ON q.QuestionID = sa.QuestionID " +
            "WHERE qs.SpeechID = #{speechID} AND sa.ListenerID = #{listenerID} AND q.Qstatus = 1 AND sa.QS = 1")
    List<Question> findQuestionsBySpeechAndListener1(@Param("speechID") int speechID, @Param("listenerID") int listenerID);

    //听众个人的已经结束的题目
    @Select("SELECT q.* FROM Question q " +
            "JOIN QueconSpe qs ON q.QuestionID = qs.QuestionID " +
            "JOIN Stuanswers sa ON q.QuestionID = sa.QuestionID " +
            "WHERE qs.SpeechID = #{speechID} AND sa.ListenerID = #{listenerID} AND q.Qstatus = 2 AND sa.QS IN (1,2) ")
    List<Question> findQuestionsBySpeechAndListener2(@Param("speechID") int speechID, @Param("listenerID") int listenerID);


    //获取听众个人本次演讲没有抽到的题目
    @Select("SELECT q.* FROM Question q " +
            "JOIN QueconSpe qs ON q.QuestionID = qs.QuestionID " +
            "JOIN Stuanswers sa ON q.QuestionID = sa.QuestionID " +
            "WHERE qs.SpeechID = #{speechID} AND sa.ListenerID = #{listenerID} AND q.Qstatus = 2 AND sa.QS = 0 ")
    List<Question> findQuestionsBySpeechAndListener3(@Param("speechID") int speechID, @Param("listenerID") int listenerID);


    //查找对应评论
    @Select("SELECT c.Comcontent, c.Comtime " +
            "FROM Comment c " +
            "WHERE c.QuestionID = #{questionID} ")
    List<Comment> findCommentsByQuestionID(int questionID);



    // 获取听众收藏的题目
    @Select("SELECT q.* FROM Question q " +
            "JOIN Collect c ON q.QuestionID = c.QuestionID " +
            "WHERE c.ListenerID = #{listenerID} AND q.Qstatus = 2")
    List<Question> findQuestionsByListenerAndStatus(@Param("listenerID") int listenerID);


    //听众进行题目收藏
    // 插入收藏记录
    @Insert("INSERT INTO Collect (ListenerID, QuestionID) VALUES (#{listenerID}, #{questionID})")
    void addToCollect(@Param("listenerID") int listenerID, @Param("questionID") int questionID);

}
