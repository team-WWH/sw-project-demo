package com.example.wwh.Mapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.*;
import java.util.List;
import com.example.wwh.pojo.Listener;
import com.example.wwh.pojo.Speaker;
import java.util.Optional;
import com.example.wwh.pojo.Speech;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
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
    @Select("SELECT * FROM speaker WHERE Smail = #{email}")
    Optional<Speaker> findSpeakerByEmail(String email);
    @Select("SELECT * FROM listener WHERE Mail = #{email}")
    Optional<Listener> findListenerByEmail(String email);
    @Select("SELECT * FROM organizer WHERE Omail = #{email}")
    Optional<Organizer> findOrganizerByEmail(String email);
    @Select("SELECT * FROM listener WHERE  ListenerID = #{id}")
    Listener findListenerByID(Integer id);


    //根据听众Mail获取个人信息
    @Select("SELECT * FROM listener WHERE Mail = #{mail}")
    Listener getListenerByMail(String mail);

    //根据演讲者mail获取个人信息
    @Select("SELECT * FROM speaker WHERE Smail = #{smail}")
    Speaker getSpeakerByMail(String smail);

    //根据组织者mail获取个人信息
    @Select("SELECT * FROM Organizer WHERE Omail = #{omail}")
    Organizer getOrganizerByMail(String omail);

    @Insert("INSERT INTO Listener (Uname,Password,Mail,Phone,Anonymous,Sex) VALUES (#{Uname},#{Password},#{Mail},#{Phone},#{Anonymous},#{Sex})")
    void addListener(Listener listener);

    @Insert("INSERT INTO speaker (Sname,Spassword,Smail,Sphone,Ssex) VALUES (#{Sname},#{Spassword},#{Smail},#{Sphone},#{Ssex})")
    void addSpeaker(Speaker speaker);

    @Insert("INSERT INTO orgnizer (Oname,Opassword,Omail,Ophone,Osex) VALUES (#{Oname},#{Opassword},#{Omail},#{Ophone},#{Osex})")
    void addOrganizer(Organizer organizer);

    // 根据 OrganizerID 获取所有对应的 Speaker 信息
    @Select("SELECT s.*" +
            "FROM speaker s " +
            "JOIN orgconspe os ON s.SpeakerID = os.SpeakerID " +
            "WHERE os.OrganizerID = #{organizerID}")
    List<Speaker> getSpeakersByOrganizerID(@Param("organizerID") int organizerID)
      
    // 根据 Smail 获取 SpeakerID
    @Select("SELECT SpeakerID FROM speaker WHERE Smail = #{smail}")
    int getSpeakerIDBySmail(@Param("smail") String smail);

    // 向 orgconspe 表插入数据
    @Insert("INSERT INTO orgconspe (OrganizerID, SpeakerID) VALUES (#{organizerID}, #{speakerID})")
    void insertOrgconspe(@Param("organizerID") int organizerID, @Param("speakerID") int speakerID);



}
