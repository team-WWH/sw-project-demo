package com.example.wwh.pojo;


//学生答案
public class Stuanswers {
    private int StuanswersID;
    private int Sanscontent;//1----A   2----B   3----C   4-----D

    private int QuestionID;
    private int State;//1----正确     0---- 错误

    public int getStuanswersID() {
        return StuanswersID;
    }

    public void setStuanswersID(int stuanswersID) {
        StuanswersID = stuanswersID;
    }

    public int getSanscontent() {
        return Sanscontent;
    }

    public void setSanscontent(int sanscontent) {
        Sanscontent = sanscontent;
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
