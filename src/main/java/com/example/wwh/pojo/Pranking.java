package com.example.wwh.pojo;



////个人排名
public class Pranking {
    private int PrankingID;
    private int ListenerID;
    private int SpeechID;
    private String rank;//排名

    public int getPrankingID() {
        return PrankingID;
    }

    public void setPrankingID(int prankingID) {
        PrankingID = prankingID;
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

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}
