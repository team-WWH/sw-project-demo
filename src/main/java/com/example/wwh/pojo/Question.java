package com.example.wwh.pojo;



//题目
public class Question {
    private int QuestionID;
    private String A;   //选项
    private String B;
    private String C;
    private String D;
    private String Questioncontent;//题目内容
    private String QresultsID;//题目分析结果ID
    private int Answer;//答案ABCD
    private int StuconQueID;//学生答案--题目链接ID
    private int ComconQueID;//评论--题目链接ID
    private int Qstatus;//题目状态1-未完成，2-已结束
    private String Answercon;//答案解析

    public int getAnswer() {
        return Answer;
    }

    public void setAnswer(int answer) {
        Answer = answer;
    }

    public String getAnswercon() {
        return Answercon;
    }

    public void setAnswercon(String answercon) {
        Answercon = answercon;
    }

    public int getQuestionID() {
        return QuestionID;
    }

    public void setQuestionID(int questionID) {
        QuestionID = questionID;
    }

    public String getA() {
        return A;
    }

    public void setA(String a) {
        A = a;
    }

    public String getB() {
        return B;
    }

    public void setB(String b) {
        B = b;
    }

    public String getC() {
        return C;
    }

    public void setC(String c) {
        C = c;
    }

    public String getD() {
        return D;
    }

    public void setD(String d) {
        D = d;
    }

    public String getQuestioncontent() {
        return Questioncontent;
    }

    public void setQuestioncontent(String questioncontent) {
        Questioncontent = questioncontent;
    }

    public String getQresultsID() {
        return QresultsID;
    }

    public void setQresultsID(String qresultsID) {
        QresultsID = qresultsID;
    }



    public int getStuconQueID() {
        return StuconQueID;
    }

    public void setStuconQueID(int stuconQueID) {
        StuconQueID = stuconQueID;
    }

    public int getComconQueID() {
        return ComconQueID;
    }

    public void setComconQueID(int comconQueID) {
        ComconQueID = comconQueID;
    }

    public int getQstatus() {
        return Qstatus;
    }

    public void setQstatus(int qstatus) {
        Qstatus = qstatus;
    }
}
