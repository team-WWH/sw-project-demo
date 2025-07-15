package com.example.wwh.pojo;


//演讲者
public class Speaker {
    private int SpeakerID;
    private String Sname;
    private String Spassword;
    private String Smail;
    private String Sphone;
    private int Ssex;   //1男

    public int getSpeakerID() {
        return SpeakerID;
    }

    public void setSpeakerID(int speakerID) {
        SpeakerID = speakerID;
    }

    public String getSname() {
        return Sname;
    }

    public void setSname(String sname) {
        Sname = sname;
    }

    public String getSpassword() {
        return Spassword;
    }

    public void setSpassword(String spassword) {
        Spassword = spassword;
    }

    public String getSmail() {
        return Smail;
    }

    public void setSmail(String smail) {
        Smail = smail;
    }

    public String getSphone() {
        return Sphone;
    }

    public void setSphone(String sphone) {
        Sphone = sphone;
    }

    public int getSsex() {
        return Ssex;
    }

    public void setSsex(int ssex) {
        Ssex = ssex;
    }
}
