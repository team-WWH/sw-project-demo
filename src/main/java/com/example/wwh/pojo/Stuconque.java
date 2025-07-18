package com.example.wwh.pojo;


//学生答案---题目
public class Stuconque {

    private int StuconQueID;
    private int StuanswersID;
    private int QuestionID;
    private int ListenerID;

    public int getStuconQueID() {
        return StuconQueID;
    }

    public void setStuconQueID(int stuconQueID) {
        StuconQueID = stuconQueID;
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

    public int getListenerID() {
        return ListenerID;
    }

    public void setListenerID(int listenerID) {
        ListenerID = listenerID;
    }
}
