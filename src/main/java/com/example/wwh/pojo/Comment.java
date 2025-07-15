package com.example.wwh.pojo;


import java.time.LocalDateTime;

//评论
public class Comment {
    private int CommentID;
    private int ListenerID;
    private int QuestionID;
    private String Comcontent;

    public int getCommentID() {
        return CommentID;
    }

    public void setCommentID(int commentID) {
        CommentID = commentID;
    }

    public int getListenerID() {
        return ListenerID;
    }

    public void setListenerID(int listenerID) {
        ListenerID = listenerID;
    }

    public int getQuestionID() {
        return QuestionID;
    }

    public void setQuestionID(int questionID) {
        QuestionID = questionID;
    }

    public String getComcontent() {
        return Comcontent;
    }

    public void setComcontent(String comcontent) {
        Comcontent = comcontent;
    }

    public LocalDateTime getComtime() {
        return Comtime;
    }

    public void setComtime(LocalDateTime comtime) {
        Comtime = comtime;
    }

    private LocalDateTime Comtime;
}
