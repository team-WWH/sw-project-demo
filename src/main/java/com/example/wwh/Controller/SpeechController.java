package com.example.wwh.Controller;

import com.example.wwh.Config.RedisUtil;
import com.example.wwh.Data.CreateResponse;
import com.example.wwh.pojo.Listener;
import com.example.wwh.pojo.Speaker;
import com.example.wwh.pojo.Speech;
import com.example.wwh.service.SpeechService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;


@RestController
public class SpeechController {

    @Autowired
    private SpeechService speechService;

    // 获取正在进行中的演讲
    @GetMapping("Listener/ongoing")
    public List<Speech> getOngoingSpeechesByListenerID(@RequestParam("ListenerID") Integer ListenerID) {
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
    @PutMapping("Speaker/createSpeech")
    public ResponseEntity<CreateResponse> CreateSpeech(String title,Integer SpeakerID){

        //int durationHours = 24;
        //Duration duration = Duration.ofHours(durationHours);
        LocalDateTime now = LocalDateTime.now();
        //LocalDateTime expiresAt = now.plus(duration);
        Speech speech = new Speech();
       // speech.setSpeechID(Integer.parseInt(speechId));
        speech.setSpeakerID(SpeakerID);
        speech.setSname(title);
        speech.setStime(now);
        speech.setSstatus(1);
        String code = speechService.CreateSpeechFirst(speech);
       CreateResponse createResponse  =new CreateResponse(speech,code);

        return ResponseEntity.ok().body(createResponse);
    }
    @GetMapping("Listener/Intend")
    public ResponseEntity<?> ComeInSpeech(String code){
        return speechService.IntendSpeech(code);
    }
    @PutMapping("   ")
    public ResponseEntity<?> endSpeech(Integer SpeechID){
        speechService.UpdateEndStatus(SpeechID);
        return ResponseEntity.ok("已经结束");
    }
    @GetMapping
    public List<Listener> AllListenerOfSpeech(Integer speechID){
        return speechService.getAllListenerBySpeech(speechID);
    }


}
