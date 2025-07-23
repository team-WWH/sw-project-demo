package com.example.wwh.pojo;


//学生答案
public class Stuanswers {
    private int StuanswersID;
    private String Sanscontent;//1----A   2----B   3----C   4-----D

    private int QuestionID;
    private int State;//1----正确     0---- 错误
    private int QS;//0---未抽到   1---抽到，未完成  2---抽到，已完成0---未抽到   1---抽到，未完成  2---抽到，已完成
    private int ListenerID;

    public int getListenerID() {
        return ListenerID;
    }

    public String getSanscontent() {
        return Sanscontent;
    }

    public void setSanscontent(String sanscontent) {
        Sanscontent = sanscontent;
    }

    public void setListenerID(int listenerID) {
        ListenerID = listenerID;
    }

    public int getQS() {
        return QS;
    }

    public void setQS(int QS) {
        this.QS = QS;
    }


    public int getStuanswersID() {
        return StuanswersID;
    }

    public void setStuanswersID(int stuanswersID) {
        StuanswersID = stuanswersID;
    }



    public int getQuestionID() {
        return QuestionID;
    }

    public void setQuestionID(int questionID) {
        QuestionID = questionID;
    }

    public int getState() {
        return State;
    }

    public void setState(int state) {
        State = state;
    }
}
