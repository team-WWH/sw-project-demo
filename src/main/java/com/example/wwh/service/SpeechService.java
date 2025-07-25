package com.example.wwh.service;

import com.example.wwh.Config.RedisUtil;
import com.example.wwh.Mapper.UserMapper;
import com.example.wwh.pojo.Listener;
import com.example.wwh.pojo.Speech;
import com.example.wwh.Mapper.SpeechMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class SpeechService {

    @Autowired
    private SpeechMapper speechMapper;
    @Autowired
    private UserMapper userMapper;
    private final Random random = new Random();
    @Value("${app.code.length}")
    private int codeLength;
    @Autowired
    RedisUtil redisutil;
    @Value("${app.code.charset}")
    private String charset;
    // 获取进行中的演讲
    public List<Speech> getOngoingSpeeches() {
        return speechMapper.getOngoingSpeeches();

    }
    // 获取进行中的演讲
    public List<Speech> getOngoingSpeechesByListenerID (Integer ListenerID){
        List<Integer> speechid = speechMapper.getSpeechidByListenerID(ListenerID);
        List<Speech> speechList = new ArrayList<>();
        for (Integer i:speechid) {
            Speech speech = speechMapper.getOngoingSpeechesByID(i);

            // 仅添加非null的演讲数据
            if (speech != null) {
                speechList.add(speech);
            }
        }
        return speechList;
    }
    public List<Speech> getOngoingSpeechesBySpeakerID (Integer SpeakerID){
        List<Integer> speechid = speechMapper.getSpeechidBySpeakerID(SpeakerID);
        System.out.println("SpeakerID"+SpeakerID);
        System.out.println("size"+speechid.size());
        List<Speech> speechList = new ArrayList<>();
        for(Integer i:speechid) {
            System.out.println("speechid"+i);
            //Speech speech = ;
           // speech.setSpeechID(i);
            Speech speech = speechMapper.getOngoingSpeechesByID(i);
            if(speech!=null)
            speechList.add(speech);
            System.out.println("speechlistsize"+speechList.size());
        }
        return speechList;
    }

    public ResponseEntity<?> IntendSpeech(String joinCode,Integer ListenerID){
        Object speechId = redisutil.get(buildJoinCodeKey(joinCode));
        speechMapper.addlisconspe((Integer) speechId,ListenerID);
        Speech speech = speechMapper.getSpeechById((Integer) speechId);
        if(speech.getSstatus()==0){
            return ResponseEntity.badRequest().body("演讲已结束");
        }

        return ResponseEntity.ok("chenggong");

    }
    public List<Speech> getEndedSpeechesBySpeakerID(Integer SpeakerID) {
        List<Integer> speechid = speechMapper.getSpeechidBySpeakerID(SpeakerID);
        List<Speech> speechList = new ArrayList<>();
        for (int i : speechid) {
            Speech speech = speechMapper.getEndedSpeechesByID(i);
            if(speech!=null)
                speechList.add(speech);
        }

        return speechList;


    }

    public List<Speech> getEndedSpeechesByListenerID(Integer ListenerID) {
        List<Integer> speechid = speechMapper.getSpeechidByListenerID(ListenerID);
        List<Speech> speechList = new ArrayList<>();
        for (Integer i : speechid) {
            Speech speech = speechMapper.getEndedSpeechesByID(i);
            if(speech!=null)
                speechList.add(speech);
        }
        System.out.println(speechList);
        return speechList;
    }



    // 获取已结束的演讲
    public List<Speech> getEndedSpeeches() {
        return speechMapper.getEndedSpeeches();
    }
    public void UpdateEndStatus(Integer SpeechID){
        speechMapper.updateEndStatus(SpeechID);
    }
    public List<Listener> getAllListenerBySpeech(Integer speechID){
        List<Integer> IDlist = speechMapper.findListenerBySpeechID(speechID);
        List<Listener> ListenerList = new ArrayList<>();
        for(int i:IDlist){
            ListenerList.add(userMapper.findListenerByID(i));

        }
        return ListenerList;
    }

    public String CreateSpeechFirst(Speech speech){
        String joinCode = generateUniqueCode();
        speechMapper.addSpeech(speech);
        System.out.println("speechid:"+speech.getSpeechID());
        storeSpeech(speech,joinCode);
        return joinCode;
    }

    private void storeSpeech(Speech speech, String joinCode) {
        // 主映射：识别码 -> 演讲ID
        redisutil.set(
                buildJoinCodeKey(joinCode),
                speech.getSpeechID(),
                86400
        );

        // 反向索引：演讲ID -> 识别码
        redisutil.set(
                buildPresentationCodeKey(String.valueOf(speech.getSpeechID())),
                joinCode,
                86400
        );
    }
    private String buildPresentationCodeKey(String presentationId) {
        return "speech:" + presentationId + ":code";
    }
    private String generateUniqueCode() {
        String code;
        do {
            code = generateRandomCode();
        } while (redisutil.hasKey(buildJoinCodeKey(code)));
        return code;
    }
    private String generateRandomCode() {
        StringBuilder sb = new StringBuilder(codeLength);
        for (int i = 0; i < codeLength; i++) {
            int index = random.nextInt(charset.length());
            sb.append(charset.charAt(index));
        }
        return sb.toString();
    }
    private String buildJoinCodeKey(String code) {
        return "join_code:" + code;
    }

    public  List<Integer> getAllQuestionIDBySpeechID(Integer SpeechID){
       return  speechMapper.getAllQuestionIDBySpeechID(SpeechID);
    }

    public List<Listener> getAllListenerBySpeechID(Integer SpeechID){
        return speechMapper.findAllListenerBySpeechID(SpeechID);
    }
    public String getCodeBySpeechID(Integer SpeechID){
        Object code = redisutil.get(buildPresentationCodeKey(String.valueOf(SpeechID)));
        return (String) code;
    }
    public void updateFilename(String filename , Integer SpeechID){
        speechMapper.updateFilename(filename,SpeechID);
    }
}

