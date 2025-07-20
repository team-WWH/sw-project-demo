package com.example.wwh.pojo;





//反馈
public class Feedback {
    private int FeedbackID;

    private int ListenerID;
    private int SpeechID;

    private String Fcontent;

    public String getFcontent() {
        return Fcontent;
    }

    public void setFcontent(String fcontent) {
        Fcontent = fcontent;
    }

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
}
