package com.example.wwh.service;
import com.example.wwh.Mapper.SpeechMapper;
import com.example.wwh.pojo.Listener;
import com.example.wwh.pojo.Speaker;
import com.example.wwh.pojo.Organizer;
import com.example.wwh.Mapper.UserMapper;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    // 根据 ListenerID 获取听众信息
    public Listener getListenerInfo(int listenerID) {
        return userMapper.getListenerById(listenerID);
    }

    // 根据 SpeakerID 获取演讲者信息
    public Speaker getSpeakerInfo(int speakerID) {
        return userMapper.getSpeakerById(speakerID);
    }

    // 根据 OrganizerID 获取组织者信息
    public Organizer getOrganizerInfo(int organizerID) {
        return userMapper.getOrganizerById(organizerID);
    }


    // 根据 Listenermail 获取听众信息
    public Listener getListenerInfoMail(String mail) {
        return userMapper.getListenerByMail(mail);
    }

    // 根据 Speakermail 获取演讲者信息
    public Speaker getSpeakerInfoMail(String smail) {
        return userMapper.getSpeakerByMail(smail);
    }

    // 根据 Organizermail 获取组织者信息
    public Organizer getOrganizerInfoMail(String omail) {
        return userMapper.getOrganizerByMail(omail);
    }

}

