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
    private int Sstatus;
    private String Sname;//演讲者名字
    private String Speechname;//演讲名字
    private String code;
    private String filename;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSpeechname() {
        return Speechname;
    }

    public void setSpeechname(String speechname) {
        Speechname = speechname;
    }

    private LocalDateTime Otime;
    private LocalDateTime Stime;

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

    public int getSstatus() {
        return Sstatus;
    }

    public void setSstatus(int sstatus) {
        Sstatus = sstatus;
    }

    public String getSname() {
        return Sname;
    }

    public void setSname(String sname) {
        Sname = sname;
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
}
