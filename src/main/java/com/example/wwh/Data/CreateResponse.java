package com.example.wwh.Data;

import com.example.wwh.pojo.Speech;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateResponse {
    private Speech speech;
    private String code;
    public CreateResponse(Speech speech, String code){
        this.code = code;
        this.speech = speech;
    }

    public Speech getSpeech() {
        return speech;
    }

    public void setSpeech(Speech speech) {
        this.speech = speech;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
