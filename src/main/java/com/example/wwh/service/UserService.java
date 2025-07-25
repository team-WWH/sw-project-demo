package com.example.wwh.service;
import com.example.wwh.Data.RegisterRequest;
import com.example.wwh.Mapper.SpeechMapper;
import com.example.wwh.pojo.Listener;
import com.example.wwh.pojo.Speaker;
import com.example.wwh.pojo.Organizer;
import com.example.wwh.Mapper.UserMapper;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.List;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;




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

     public void addListener(RegisterRequest request){
        Listener listener = new Listener();
        listener.setMail(request.getMail());
        listener.setAnonymous(0);
        listener.setPhone(request.getPhone());
        listener.setUname(request.getUname());
        listener.setSex(Integer.parseInt(request.getSex()));
        listener.setPassword(request.getPassword());
        userMapper.addListener(listener);
    }
    public void addSpeaker(RegisterRequest request){
        Speaker listener = new Speaker();
        listener.setSmail(request.getMail());
        listener.setSphone(request.getPhone());
        listener.setSname(request.getUname());
        listener.setSsex(Integer.parseInt(request.getSex()));
        listener.setSpassword(request.getPassword());
        userMapper.addSpeaker(listener);
    }
    public void addOrganizer(RegisterRequest request){
        Organizer listener = new Organizer();
        listener.setOmail(request.getMail());
        listener.setOphone(request.getPhone());
        listener.setOname(request.getUname());
        listener.setOsex(Integer.parseInt(request.getSex()));
        listener.setOpassword(request.getPassword());
        userMapper.addOrganizer(listener);
    }

    // 根据 OrganizerID 获取所有 Speaker 信息
    public List<Speaker> getSpeakersByOrganizer(int organizerID) {
        return userMapper.getSpeakersByOrganizerID(organizerID);
    }


//添加组织者手下的演讲者
    public void addOrgconspe(int organizerID, String smail) {
        // 获取 SpeakerID
        int speakerID = userMapper.getSpeakerIDBySmail(smail);

        // 插入数据到 orgconspe 表
        userMapper.insertOrgconspe(organizerID, speakerID);
    }


}

