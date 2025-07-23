package com.example.wwh.Data;

import com.example.wwh.pojo.Question;
import com.example.wwh.pojo.Stuanswers;
import lombok.Data;

@Data
public class StuanwersResponse {
    private Integer StuanswersID;
    private String  Questioncontent;
    private String A;
    private String B;
    private String C;
    private String D;
    private Integer QuestionID;

    public Integer getStuanswersID() {
        return StuanswersID;
    }

    public void setStuanswersID(Integer stuanswersID) {
        StuanswersID = stuanswersID;
    }

    public String getQuestioncontent() {
        return Questioncontent;
    }

    public void setQuestioncontent(String questioncontent) {
        Questioncontent = questioncontent;
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

    public Integer getQuestionID() {
        return QuestionID;
    }

    public void setQuestionID(Integer questionID) {
        QuestionID = questionID;
    }
}
