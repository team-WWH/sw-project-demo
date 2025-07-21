package com.example.wwh.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

//题目
@JsonIgnoreProperties(ignoreUnknown = true)
public class Question {

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
    private int StuconQueID;//学生答案--题目链接ID
    private int ComconQueID;//评论--题目链接ID
    private int Qstatus;
    @JsonProperty("Analysis")
    private String Analysis;

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

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String Answer) {
        this.Answer = Answer;
    }

    public int getStuconQueID() {
        return StuconQueID;
    }

    public void setStuconQueID(int StuconQueID) {
        this.StuconQueID = StuconQueID;
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

    public String getAnalysis() {
        return Analysis;
    }

    public void setAnalysis(String Analysis) {
        this.Analysis = Analysis;
    }
}
