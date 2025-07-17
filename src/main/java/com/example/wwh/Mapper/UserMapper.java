package com.example.wwh.Mapper;

import com.example.wwh.pojo.Listener;
import com.example.wwh.pojo.Organizer;
import com.example.wwh.pojo.Speaker;
import com.example.wwh.pojo.Speech;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;


public interface UserMapper {

    @Select("SELECT * FROM speaker WHERE Smail = #{email}")
    Optional<Speaker> findSpeakerByEmail(String email);
    @Select("SELECT * FROM listener WHERE Mail = #{email}")
    Optional<Listener> findListenerByEmail(String email);
    @Select("SELECT * FROM organizer WHERE Omail = #{email}")
    Optional<Organizer> findOrganizerByEmail(String email);



}
