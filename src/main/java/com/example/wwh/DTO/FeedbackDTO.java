package com.example.wwh.DTO;

public class FeedbackDTO {
    private int FeedbackID;

    private int ListenerID;
    private int SpeechID;

    private String Fcontent;
    private String Uname;
    private int Anonymous;//匿名选项1为匿，0不

    public int getFeedbackID() {
        return FeedbackID;
    }

    public void setFeedbackID(int feedbackID) {
        FeedbackID = feedbackID;
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

    public String getFcontent() {
        return Fcontent;
    }

    public void setFcontent(String fcontent) {
        Fcontent = fcontent;
    }

    public String getUname() {
        return Uname;
    }

    public void setUname(String uname) {
        Uname = uname;
    }

    public int getAnonymous() {
        return Anonymous;
    }

    public void setAnonymous(int anonymous) {
        Anonymous = anonymous;
    }
}
