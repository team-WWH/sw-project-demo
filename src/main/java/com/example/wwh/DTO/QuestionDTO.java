package com.example.wwh.DTO;

import com.example.wwh.pojo.Comment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
public class QuestionDTO
{

    @Getter

    private String Sanscontent;//1----A   2----B   3----C   4-----D

    private int State;//1----正确     0---- 错误
    private int QuestionID;
    @JsonProperty("A")
    private String A;   //选项

    @JsonProperty("B")
    private String B;
    @JsonProperty("C")
    private String C;
    @JsonProperty("D")
    private String D;
    @JsonProperty("Questioncontent")
    private String Questioncontent;//题目内容
    private String QresultsID;//题目分析结果ID
    @JsonProperty("Answer")
    private String Answer;//答案

    private int ComconQueID;//评论--题目链接ID
    private int Qstatus;
    @JsonProperty("Analysis")
    private String Answercon;//答案解析
    private LocalDateTime Qtime;
    private int StuanswersID;

    public int getStuanswersID() {
        return StuanswersID;
    }

    public void setStuanswersID(int stuanswersID) {
        StuanswersID = stuanswersID;
    }

    public LocalDateTime getQtime() {
        return Qtime;
    }

    public void setQtime(LocalDateTime qtime) {
        Qtime = qtime;
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

    public void setQuestionID(int QuestionID) {
        this.QuestionID = QuestionID;
    }

    public String getA() {
        return A;
    }

    public void setA(String A) {
        this.A = A;
    }

    public String getB() {
        return B;
    }

    public void setB(String B) {
        this.B = B;
    }

    public String getC() {
        return C;
    }

    public void setC(String C) {
        this.C = C;
    }

    public String getD() {
        return D;
    }

    public void setD(String D) {
        this.D = D;
    }

    public String getQuestioncontent() {
        return Questioncontent;
    }

    public void setQuestioncontent(String Questioncontent) {
        this.Questioncontent = Questioncontent;
    }

    public String getQresultsID() {
        return QresultsID;
    }

    public void setQresultsID(String QresultsID) {
        this.QresultsID = QresultsID;
    }


    public void setAnswer(String Answer) {
        this.Answer = Answer;
    }



    public int getComconQueID() {
        return ComconQueID;
    }

    public void setComconQueID(int ComconQueID) {
        this.ComconQueID = ComconQueID;
    }

    public int getQstatus() {
        return Qstatus;
    }

    public void setQstatus(int Qstatus) {
        this.Qstatus = Qstatus;
    }

    public int getState() {
        return State;
    }

    public void setState(int state) {
        State = state;
    }
    public String getSanscontent() {
        return Sanscontent;
    }

    public void setSanscontent(String sanscontent) {
        Sanscontent = sanscontent;
    }

    public String getAnswer() {
        return Answer;
    }
}
