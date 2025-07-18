package com.example.wwh.Controller;

import com.example.wwh.pojo.Listener;
import com.example.wwh.pojo.Speech;
import com.example.wwh.service.SpeechService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
public class SpeechController {

    @Autowired
    private SpeechService speechService;

    // 获取正在进行中的演讲
    @GetMapping("Listener/ongoing")
    public List<Speech> getOngoingSpeechesByListenerID(@Param("ListenerID") Integer ListenerID) {
        return speechService.getOngoingSpeechesByListenerID(ListenerID);
    }
    @GetMapping("Speaker/ongoing")
    public List<Speech> getOngoingSpeechesBySpeakerID(@Param("SpeakerID") Integer SpeakerID) {
        return speechService.getOngoingSpeechesBySpeakerID(SpeakerID);
    }
    // 获取已结束的演讲
    @GetMapping("Listener/ended")
    public List<Speech> getEndedSpeechesByListenerID(@Param("ListenerID") Integer ListenerID) {
        return speechService.getEndedSpeechesByListenerID(ListenerID);
    }
    @GetMapping("Speaker/ended")
    public List<Speech> getEndedSpeechesBySpeakerID(@Param("SpeakerID") Integer SpeakerID) {
        return speechService.getEndedSpeechesBySpeakerID(SpeakerID);
    }
//    @PostMapping("Speaker/createSpeech")
//    public ResponseEntity<String> CreateSpeech(){
//
//
//    }
    @GetMapping
    public List<Listener> AllListenerOfSpeech(Integer speechID){
        return speechService.getAllListenerBySpeech(speechID);
    }


}
