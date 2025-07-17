package com.example.wwh.Mapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;
import com.example.wwh.pojo.Listener;
import com.example.wwh.pojo.Speaker;
import com.example.wwh.pojo.Organizer;
import com.example.wwh.pojo.Feedback;
@Mapper
public interface FeedbackMapper {


    //提交更新反馈
    @Insert("INSERT INTO feedback (Fcontent, ListenerID, SpeechID) VALUES (#{Fcontent}, #{ListenerID}, #{SpeechID})")
    void submitFeedback(Feedback feedback);


}
