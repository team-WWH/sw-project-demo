package com.example.wwh.Controller;

import com.example.wwh.Config.RedisUtil;
import com.example.wwh.Data.CreateResponse;
import com.example.wwh.pojo.Listener;
import com.example.wwh.pojo.Speaker;
import com.example.wwh.pojo.Speech;
import com.example.wwh.service.MinioService;
import com.example.wwh.service.SpeechService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;


@RestController
public class SpeechController {

    @Autowired
    private SpeechService speechService;
    @Autowired
    private MinioService minioService;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    // 获取正在进行中的演讲
    @GetMapping("Listener/ongoing")
    public List<Speech> getOngoingSpeechesByListenerID(@RequestParam("ListenerID") Integer ListenerID) {
        List<Speech> speeches = speechService.getOngoingSpeechesByListenerID(ListenerID);
        for(Speech speech:speeches){
            // System.out.println(speeches);
            Integer i = speech.getSpeechID();
            String code =  speechService.getCodeBySpeechID(i);
            speech.setCode(code);
        }
        return speeches;
    }
    @GetMapping("Comment/Speaker/ongoing")
    public List<Speech> getOngoingSpeechesBySpeakerID(@Param("SpeakerID") Integer SpeakerID) {
       List<Speech> speeches = speechService.getOngoingSpeechesBySpeakerID(SpeakerID);
        //System.out.println("speeches"+speeches.size());
       for(Speech speech:speeches){
          // System.out.println(speeches);
           Integer i = speech.getSpeechID();
          String code =  speechService.getCodeBySpeechID(i);
          speech.setCode(code);
       }
       return speeches;

    }
    // 获取已结束的演讲
    @GetMapping("Listener/ended")
    public List<Speech> getEndedSpeechesByListenerID(@Param("ListenerID") Integer ListenerID) {
        List<Speech> speeches = speechService.getEndedSpeechesByListenerID(ListenerID);
        //System.out.println("speeches"+speeches.size());
        for(Speech speech:speeches){
            // System.out.println(speeches);
            Integer i = speech.getSpeechID();
            String code =  speechService.getCodeBySpeechID(i);
            speech.setCode(code);
        }
        return speeches;
    }
    @GetMapping("Comment/Speaker/ended")
    public List<Speech> getEndedSpeechesBySpeakerID(@Param("SpeakerID") Integer SpeakerID) {
        List<Speech> speeches =speechService.getEndedSpeechesBySpeakerID(SpeakerID);
        //System.out.println("speeches"+speeches.size());
        for(Speech speech:speeches){
            // System.out.println(speeches);
            Integer i = speech.getSpeechID();
            String code =  speechService.getCodeBySpeechID(i);
            speech.setCode(code);
        }
        return speeches;
    }
    @PostMapping("Speaker/addfile")
    public ResponseEntity<String> addFile(@Param("title") String title,
                                          @Param("SpeakerID") Integer SpeakerID,
                                          @Param("file") MultipartFile file,
                                          @Param("type") String type,
                                          @Param("SpeechID") Integer SpeechID) throws Exception {
        String filename = title + SpeakerID+'.'+type;
        minioService.uploadFile(file,filename);
        System.out.println("addfileid"+SpeechID);
        speechService.updateFilename(filename,SpeechID);
        return ResponseEntity.ok("文件添加成功");
    }
    @GetMapping("Comment/getfile")
    public ResponseEntity<String> getFile(@Param("filename") String filename) throws Exception {
        byte[] file = minioService.downloadFile(filename);
        return ResponseEntity.ok("获取成功");
    }

    @PutMapping("Speaker/createSpeech")
    public ResponseEntity<CreateResponse> CreateSpeech(@RequestParam("title") String title,@RequestParam("SpeakerID") Integer SpeakerID) throws Exception {
        System.out.println("调用");
        System.out.println(title);
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
    public ResponseEntity<?> ComeInSpeech(@Param("code") String code,
                                            @Param("ListenerID") Integer ListenerID){
        return speechService.IntendSpeech(code,ListenerID);
    }
    @PutMapping("Speaker/EndSpeech")
    public ResponseEntity<?> endSpeech(@Param("SpeechID") Integer SpeechID){
        speechService.UpdateEndStatus(SpeechID);
        String topic = "/topic/chat/" + SpeechID;
        messagingTemplate.convertAndSend(topic, "SPEECH_END");
        return ResponseEntity.ok("已经结束");
    }
    @GetMapping("Comment/Speaker/AllListener")
    public List<Listener> AllListenerOfSpeech(Integer speechID){
        return speechService.getAllListenerBySpeech(speechID);
    }
    @GetMapping("/Comment/getfileurl")
    public String getPresignedUrl( @Param("filename") String filename) {
        System.out.println("#####################################################");
        int expiryTimeInSeconds = 36000; // URL 有效期为 1 小时
        String url =  minioService.getPresignedUrl( filename, expiryTimeInSeconds);
        System.out.println(url);
        return url;
    }
    @GetMapping("Speaker/getCode")
    public ResponseEntity<String> getCode(Integer SpeechID){
        String code = speechService.getCodeBySpeechID(SpeechID);
        return ResponseEntity.ok(code);
    }


}
