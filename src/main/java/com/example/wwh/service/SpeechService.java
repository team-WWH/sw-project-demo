package com.example.wwh.service;

import com.example.wwh.Mapper.UserMapper;
import com.example.wwh.pojo.Listener;
import com.example.wwh.pojo.Speech;
import com.example.wwh.Mapper.SpeechMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class SpeechService {

    @Autowired
    private SpeechMapper speechMapper;
    @Autowired
    private UserMapper userMapper;

    // 获取进行中的演讲
    public List<Speech> getOngoingSpeeches() {
        return speechMapper.getOngoingSpeeches();
    }

    // 获取已结束的演讲
    public List<Speech> getEndedSpeeches() {
        return speechMapper.getEndedSpeeches();
    }

    public List<Listener> getAllListenerBySpeech(Integer speechID){
        List<Integer> IDlist = speechMapper.findListenerBySpeechID(speechID);
        List<Listener> ListenerList = new ArrayList<>();
        for(int i:IDlist){
            ListenerList.add(userMapper.findListenerByID(i));
        }
        return ListenerList;
    }
}
