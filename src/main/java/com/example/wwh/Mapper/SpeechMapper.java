package com.example.wwh.Mapper;
import com.example.wwh.pojo.Speech;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
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

        @Select("SELECT SpeechID FROM lisconspe WHERE ListenerID = #{ListenerID}")
        List<Integer> getSpeechidByListenerID(Integer ListenerID);

        @Select("SELECT SpeechID FROM speech WHERE SpeakerID = #{SpeakerID}")
        List<Integer> getSpeechidBySpeakerID(Integer SpeakerID);

}

