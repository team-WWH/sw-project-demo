package com.example.wwh.Mapper;

import com.example.wwh.pojo.Stuanswers;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface StuAnswerMapper {

    // 根据 ListenerID 和 QuestionID 获取答案数据(听众进入某个题目要展示)
    @Select("SELECT * FROM stuanswers WHERE ListenerID = #{listenerID} AND QuestionID = #{questionID}")
    Stuanswers findAnswerByListenerAndQuestion(@Param("listenerID") int listenerID, @Param("questionID") int questionID);

    @Insert("INSERT INTO stuanswers (Sanscontent,QuestionID,State,QS,ListenerID) VALUES (0,#{QuestionID},#{State},#{QS},#{ListenerID})")
    void addstuAnswer(Stuanswers stuanswers);

    @Select("SELECT * FROM stuanswers WHERE QuestionID = #{QuestionID} AND QS != 0 ")
    List<Stuanswers> getStuanswerByQuestionID(Integer QuestionID);

    @Update("UPDATE stuanswers SET QS = 2, State = #{State},Sanscontent=#{answers} WHERE StuanswersID=#{StuanswersID} ")
    void updataAnswersByID(Integer StuanswersID,String answers,Integer State);
}
