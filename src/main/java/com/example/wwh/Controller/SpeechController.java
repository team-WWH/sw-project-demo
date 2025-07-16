package com.example.wwh.Controller;

import com.example.wwh.pojo.Speech;
import com.example.wwh.service.SpeechService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
@RequestMapping("/speeches")
public class SpeechController {

    @Autowired
    private SpeechService speechService;

    // 获取正在进行中的演讲
    @GetMapping("/ongoing")
    public List<Speech> getOngoingSpeeches() {
        return speechService.getOngoingSpeeches();
    }

    // 获取已结束的演讲
    @GetMapping("/ended")
    public List<Speech> getEndedSpeeches() {
        return speechService.getEndedSpeeches();
    }
}
