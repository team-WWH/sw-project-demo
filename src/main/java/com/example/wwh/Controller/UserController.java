package com.example.wwh.Controller;
import com.example.wwh.pojo.Listener;
import com.example.wwh.pojo.Speaker;
import com.example.wwh.pojo.Organizer;
import com.example.wwh.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")



public class UserController {


    @Autowired
    private UserService userService;

    // 根据 ListenerID 获取听众信息
    @GetMapping("/listener/{listenerID}")
    public Listener getListenerById(@PathVariable int listenerID) {
        System.out.println("Received request for ListenerID: " + listenerID);  // 打印日志
        Listener listener = userService.getListenerInfo(listenerID);
        System.out.println("Fetched listener info: " + listener);  // 打印查询结果
        return listener;
    }

    // 根据 SpeakerID 获取演讲者信息
    @GetMapping("/speaker/{speakerID}")
    public Speaker getSpeakerById(@PathVariable int speakerID) {
        return userService.getSpeakerInfo(speakerID);
    }

    // 根据 ListenerID 获取听众信息
    @GetMapping("/organizer/{organizerID}")
    public Organizer getOrganizerById(@PathVariable int organizerID) {
        return userService.getOrganizerInfo(organizerID);
    }


    // 根据 ListenerMail 获取听众信息
    @GetMapping("/listener/mail/{mail}")
    public Listener getListenerByMail(@PathVariable String mail) {
        System.out.println("Received request for ListenerID: " + mail);  // 打印日志
        Listener listener = userService.getListenerInfoMail(mail);
        System.out.println("Fetched listener info: " + listener);  // 打印查询结果
        return listener;
    }

    // 根据 SpeakerMail 获取听众信息
    @GetMapping("/speaker/mail/{smail}")
    public Speaker getSpeakerBySmail(@PathVariable String smail) {
        return userService.getSpeakerInfoMail(smail);
    }

    // 根据 ListenerMail 获取听众信息
    @GetMapping("/organizer/mail/{omail}")
    public Organizer getOrganizerByOmail(@PathVariable String omail) {
        return userService.getOrganizerInfoMail(omail);
    }


    // 根据 OrganizerID 获取所有 Speaker 信息
    @GetMapping("/organizer/getSpeakers")
    public List<Speaker> getSpeakers(@RequestParam("OrganizerID") int organizerID) {
        return userService.getSpeakersByOrganizer(organizerID);
    }
}
