package com.example.wwh.Mapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;
import com.example.wwh.pojo.Listener;
import com.example.wwh.pojo.Speaker;
import com.example.wwh.pojo.Organizer;
@Mapper
public interface UserMapper {

    //根据听众ID获取个人信息
    @Select("SELECT * FROM listener WHERE ListenerID = #{listenerID}")
    Listener getListenerById(int listenerID);
    /*@Select("SELECT ListenerID, Uname, Password, Mail, Phone, Anonymous, Sex, CollectID FROM listener WHERE ListenerID = #{listenerID}")
    Listener getListenerById(int listenerID);*/



    //根据演讲者ID获取个人信息
    @Select("SELECT * FROM speaker WHERE SpeakerID = #{speakerID}")
    Speaker getSpeakerById(int speakerID);

    //根据组织者ID获取个人信息
    @Select("SELECT * FROM organizer WHERE OrganizerID = #{organizerID}")
    Organizer getOrganizerById(int organizerID);



    //根据听众Mail获取个人信息
    @Select("SELECT * FROM listener WHERE Mail = #{mail}")
    Listener getListenerByMail(String mail);

    //根据演讲者mail获取个人信息
    @Select("SELECT * FROM speaker WHERE Smail = #{smail}")
    Speaker getSpeakerByMail(String smail);

    //根据组织者mail获取个人信息
    @Select("SELECT * FROM Organizer WHERE Omail = #{omail}")
    Organizer getOrganizerByMail(String omail);

}
