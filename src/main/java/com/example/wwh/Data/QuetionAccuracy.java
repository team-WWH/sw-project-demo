package com.example.wwh.Data;

import com.example.wwh.pojo.Question;
import lombok.Data;

@Data
public class QuetionAccuracy {
    private Integer QuestionID;
    private double Accuracy;

    public Integer getQuestionID() {
        return QuestionID;
    }

    public void setQuestionID(Integer questionID) {
        QuestionID = questionID;
    }

    public double getAccuracy() {
        return Accuracy;
    }

    public void setAccuracy(double accuracy) {
        Accuracy = accuracy;
    }
}
