package com.example.wwh.pojo;


//测试分析结果
public class Tresults {
    private int TresultsID;
    private float Tcorrectrate;//正确率
    private String Trank;//位次

    private int ListenerID;
    private int SpeechID;
    private int Totalpeo;//总人数

    public int getTresultsID() {
        return TresultsID;
    }

    public void setTresultsID(int tresultsID) {
        TresultsID = tresultsID;
    }

    public float getTcorrectrate() {
        return Tcorrectrate;
    }

    public void setTcorrectrate(float tcorrectrate) {
        Tcorrectrate = tcorrectrate;
    }

    public String getTrank() {
        return Trank;
    }

    public void setTrank(String trank) {
        Trank = trank;
    }

    public int getListenerID() {
        return ListenerID;
    }

    public void setListenerID(int listenerID) {
        ListenerID = listenerID;
    }

    public int getSpeechID() {
        return SpeechID;
    }

    public void setSpeechID(int speechID) {
        SpeechID = speechID;
    }

    public int getTotalpeo() {
        return Totalpeo;
    }

    public void setTotalpeo(int totalpeo) {
        Totalpeo = totalpeo;
    }
}
