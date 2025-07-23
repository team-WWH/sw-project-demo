package com.example.wwh.Data;

import lombok.Data;

@Data
public class AccurayAndNumber {
   private Integer number;
   private double Accuray;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public double getAccuray() {
        return Accuray;
    }

    public void setAccuray(double accuray) {
        Accuray = accuray;
    }
}
