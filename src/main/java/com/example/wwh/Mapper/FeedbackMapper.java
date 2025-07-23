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

    // 获取与 SpeechID 相关的所有 ListenerID
    @Select("SELECT DISTINCT ListenerID FROM feedback WHERE SpeechID = #{speechID}")
    List<Integer> getListenersBySpeechID(int speechID);

    // 获取与指定 SpeechID 和 ListenerID 相关的所有历史消息
    @Select("SELECT FeedbackID, Fcontent, ListenerID, SpeechID FROM feedback WHERE SpeechID = #{speechID}  ORDER BY FeedbackID ASC")
    List<Feedback> getMessagesBySpeechAndListener(int speechID);
}
