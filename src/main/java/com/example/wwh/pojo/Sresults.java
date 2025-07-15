package com.example.wwh.pojo;


//演讲分析结果
public class Sresults {
    private int SresultsID;
    private int SpeechID;
    private float Scorrectrate;//正确率

    public int getSresultsID() {
        return SresultsID;
    }

    public void setSresultsID(int sresultsID) {
        SresultsID = sresultsID;
    }

    public int getSpeechID() {
        return SpeechID;
    }

    public void setSpeechID(int speechID) {
        SpeechID = speechID;
    }

    public float getScorrectrate() {
        return Scorrectrate;
    }

    public void setScorrectrate(float scorrectrate) {
        Scorrectrate = scorrectrate;
    }
}
