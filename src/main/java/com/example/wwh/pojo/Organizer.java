package com.example.wwh.pojo;


//组织者
public class Organizer {
    private int OrganizerID;
    private String Oname;
    private String Opassword;
    private String Omail;
    private String Ophone;
    private int Osex;//1男  0女

    public int getOrganizerID() {
        return OrganizerID;
    }

    public void setOrganizerID(int organizerID) {
        OrganizerID = organizerID;
    }

    public String getOname() {
        return Oname;
    }

    public void setOname(String oname) {
        Oname = oname;
    }

    public String getOpassword() {
        return Opassword;
    }

    public void setOpassword(String opassword) {
        Opassword = opassword;
    }

    public String getOmail() {
        return Omail;
    }

    public void setOmail(String omail) {
        Omail = omail;
    }

    public String getOphone() {
        return Ophone;
    }

    public void setOphone(String ophone) {
        Ophone = ophone;
    }

    public int getOsex() {
        return Osex;
    }

    public void setOsex(int osex) {
        Osex = osex;
    }
}
