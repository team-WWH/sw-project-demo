package com.example.wwh.Mapper;
import com.example.wwh.pojo.Listener;
import com.example.wwh.pojo.Speech;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SpeechMapper {



        // 获取正在进行中的演讲并返回讲者姓名
        @Select("SELECT s.SpeechID, s.SpeakerID, s.OrganizerID, s.LisconSpeID, s.QueconSpeID, " +
                "s.SresultsID, s.ClaconSpeID, s.Sstatus, s.Otime, s.Stime, sp.Sname " +
                "FROM speech s " +
                "JOIN speaker sp ON s.SpeakerID = sp.SpeakerID " +
                "WHERE s.Sstatus = 1")
        List<Speech> getOngoingSpeeches();

        // 获取已结束的演讲并返回讲者姓名
        @Select("SELECT s.SpeechID, s.SpeakerID, s.OrganizerID, s.LisconSpeID, s.QueconSpeID, " +
                "s.SresultsID, s.ClaconSpeID, s.Sstatus, s.Otime, s.Stime, sp.Sname " +
                "FROM speech s " +
                "JOIN speaker sp ON s.SpeakerID = sp.SpeakerID " +
                "WHERE s.Sstatus = 0")
        List<Speech> getEndedSpeeches();

        @Select("SELECT * FROM speech WHERE Sstatus = 1 AND SpeechID = #{speechID}")
        Speech getOngoingSpeechesByID(Integer speechID);

        @Select("SELECT * FROM speech WHERE Sstatus = 0 AND  SpeechID = #{speechID}")
        Speech getEndedSpeechesByID(Integer speechID);

        @Select("SELECT ListenerID FROM lisconspe WHERE SpeechID = #{speechID}")
        List<Integer> findListenerBySpeechID(Integer speechID);

        @Select("SELECT l.ListenerID,l.Uname,l.Mail,l.Phone,l.Anonymous,l.Sex" +
                " FROM Listener l " +
                "JOIN lisconspe lc ON l.ListenerID = lc.ListenerID " +
                "WHERE lc.SpeechID = #{speechID} ")
        List<Listener> findAllListenerBySpeechID(Integer speechID);

        @Select("SELECT SpeechID FROM lisconspe WHERE ListenerID = #{ListenerID}")
        List<Integer> getSpeechidByListenerID(Integer ListenerID);

        @Select("SELECT SpeechID FROM speech WHERE SpeakerID = #{SpeakerID}")
        List<Integer> getSpeechidBySpeakerID(Integer SpeakerID);

        @Insert("INSERT INTO speech (SpeakerID,Sstatus,Stime,Speechname) " +
                "VALUES (#{SpeakerID},#{Sstatus},#{Stime},#{Sname})")
        @Options(useGeneratedKeys = true, keyProperty = "SpeechID")
        void addSpeech(Speech speech);

        @Select("SELECT * FROM speech WHERE SpeechID = #{SpeechID}")
        Speech getSpeechById(Integer SpeechID);

        @Update("UPDATE speech SET Sstatus = 0   WHERE SpeechID = #{SpeechID}")
        void updateEndStatus(Integer SpeechID);

        @Select("SELECT QuestionID FROM queconspe WHERE SpeechID = #{SpeechID}")
        List<Integer> getAllQuestionIDBySpeechID(Integer SpeechID);
}

