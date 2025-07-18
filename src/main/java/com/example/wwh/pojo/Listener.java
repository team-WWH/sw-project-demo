package com.example.wwh.pojo;
import com.fasterxml.jackson.annotation.JsonProperty;

///听众
public class Listener {
    private int ListenerID;

    private String Uname;
    private String Password;

    private String Mail;
    private String Phone;
    private int Anonymous;//匿名选项1为匿，0不
    private int Sex;//1男  0女
    private int CollectID;//收藏题目库ID

    public int getListenerID() {
        return ListenerID;
    }

    public void setListenerID(int listenerID) {
        ListenerID = listenerID;
    }

    public String getUname() {
        return Uname;
    }

    public void setUname(String uname) {
        Uname = uname;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        Mail = mail;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public int getAnonymous() {
        return Anonymous;
    }

    public void setAnonymous(int anonymous) {
        Anonymous = anonymous;
    }

    public int getSex() {
        return Sex;
    }

    public void setSex(int sex) {
        Sex = sex;
    }

    public int getCollectID() {
        return CollectID;
    }

    public void setCollectID(int collectID) {
        CollectID = collectID;
    }
}
