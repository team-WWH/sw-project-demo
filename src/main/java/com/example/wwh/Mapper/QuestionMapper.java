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
    @Select("SELECT q.QuestionID, q.Questioncontent, q.Answer " +
            "FROM Question q " +
            "LEFT JOIN Stuanswers sa ON q.QuestionID = sa.QuestionID " +
            "LEFT JOIN QueconSpe qs ON q.QuestionID = qs.QuestionID " +
            "LEFT JOIN Comment c ON q.QuestionID = c.QuestionID " +
            "WHERE qs.SpeechID = #{speechID} AND c.ListenerID = #{listenerID} " +
            "AND qs.QS IN (1, 2) AND c.Qstatus = 2")
    @Results({
            @Result(property = "questionID", column = "QuestionID"),
            @Result(property = "questionContent", column = "Questioncontent"),
            @Result(property = "answer", column = "Answer"),
            // 使用 @Many 注解来加载多个评论
            @Result(property = "comments", column = "QuestionID",
                    many = @Many(select = "findCommentsByQuestionID"))
    })
    List<QuestionDTO> findQuestionsBySpeechAndListener2(@Param("speechID") int speechID,
                                                        @Param("listenerID") int listenerID);

    //查找对应评论
    @Select("SELECT c.Comcontent, c.Comtime " +
            "FROM Comment c " +
            "WHERE c.QuestionID = #{questionID} AND c.Qstatus = 2")
    List<Comment> findCommentsByQuestionID(int questionID);


}
