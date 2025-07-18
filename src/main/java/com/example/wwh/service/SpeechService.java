package com.example.wwh.service;

import com.example.wwh.pojo.Speech;
import com.example.wwh.Mapper.SpeechMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class SpeechService {

    @Autowired
    private SpeechMapper speechMapper;

    // 获取进行中的演讲
    public List<Speech> getOngoingSpeeches() {
        return speechMapper.getOngoingSpeeches();
    }

    // 获取已结束的演讲
    public List<Speech> getEndedSpeeches() {
        return speechMapper.getEndedSpeeches();
    }
}
