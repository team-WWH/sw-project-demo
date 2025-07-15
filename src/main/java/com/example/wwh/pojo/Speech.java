package com.example.wwh.pojo;


import java.time.LocalDateTime;

//演讲
public class Speech {
    private int SpeechID;
    private int SpeakerID;
    private int OrganizerID;
    private int LisconSpeID;//演讲--听众
    private int QueconSpeID;//演讲--题目
    private int SresultsID;//演讲分析ID
    private int ClaconSpeID;//上课数据---演讲ID
    private int Sstatus;//1进行中   0已结束

    public int getSpeechID() {
        return SpeechID;
    }

    public void setSpeechID(int speechID) {
        SpeechID = speechID;
    }

    public int getSpeakerID() {
        return SpeakerID;
    }

    public void setSpeakerID(int speakerID) {
        SpeakerID = speakerID;
    }

    public int getOrganizerID() {
        return OrganizerID;
    }

    public void setOrganizerID(int organizerID) {
        OrganizerID = organizerID;
    }

    public int getLisconSpeID() {
        return LisconSpeID;
    }

    public void setLisconSpeID(int lisconSpeID) {
        LisconSpeID = lisconSpeID;
    }

    public int getQueconSpeID() {
        return QueconSpeID;
    }

    public void setQueconSpeID(int queconSpeID) {
        QueconSpeID = queconSpeID;
    }

    public int getSresultsID() {
        return SresultsID;
    }

    public void setSresultsID(int sresultsID) {
        SresultsID = sresultsID;
    }

    public int getClaconSpeID() {
        return ClaconSpeID;
    }

    public void setClaconSpeID(int claconSpeID) {
        ClaconSpeID = claconSpeID;
    }

    public LocalDateTime getOtime() {
        return Otime;
    }

    public void setOtime(LocalDateTime otime) {
        Otime = otime;
    }

    public LocalDateTime getStime() {
        return Stime;
    }

    public void setStime(LocalDateTime stime) {
        Stime = stime;
    }

    private LocalDateTime Otime;
    private LocalDateTime Stime;
}
