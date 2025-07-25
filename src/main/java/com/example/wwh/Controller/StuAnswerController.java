package com.example.wwh.Controller;

import com.example.wwh.pojo.Stuanswers;
import com.example.wwh.service.StuAnswerService;
import org.springframework.web.bind.annotation.*;
import com.example.wwh.pojo.Listener;
import com.example.wwh.pojo.Speech;
import com.example.wwh.service.SpeechService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class StuAnswerController {

    @Autowired
    private StuAnswerService stuanswersService;


    // 获取根据 ListenerID 和 QuestionID 筛选的答案(听众进入某个题目要展示)
    @GetMapping("/listener/getAnswer")
    public ResponseEntity<Stuanswers> getAnswer(
            @RequestParam int listenerID,
            @RequestParam int questionID) {

        // 调用 service 层获取数据
        Stuanswers answer = stuanswersService.getAnswerByListenerAndQuestion(listenerID, questionID);
        return ResponseEntity.ok(answer);  // 返回数据
    }

    @GetMapping("Listener/UpdataAnswers")
    public ResponseEntity<Map<String, String>> updataStuanswers(
            @RequestParam Integer StuanswersID,
            @RequestParam String answers,
            @RequestParam Integer State) {

        stuanswersService.updataAnswers(StuanswersID, answers, State);

        // 返回一个包含状态信息的 JSON 对象
        Map<String, String> response = new HashMap<>();
        response.put("message", "更改成功");

        return ResponseEntity.ok(response);  // 返回 JSON 格式的响应

    }



}
