package com.example.wwh.pojo;



//题目分析结果
public class Qresults {
    private int QresultsID;
    private int SpeechID;
    private float Correctrate;//正确率

    public int getQresultsID() {
        return QresultsID;
    }

    public void setQresultsID(int qresultsID) {
        QresultsID = qresultsID;
    }

    public int getSpeechID() {
        return SpeechID;
    }

    public void setSpeechID(int speechID) {
        SpeechID = speechID;
    }

    public float getCorrectrate() {
        return Correctrate;
    }

    public void setCorrectrate(float correctrate) {
        Correctrate = correctrate;
    }
}
