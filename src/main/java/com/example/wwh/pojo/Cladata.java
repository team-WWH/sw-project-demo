package com.example.wwh.pojo;


//上课数据
public class Cladata {
    private int CladataID;
    private String Datatype;//类型
    private String datalink;//链接

    public int getCladataID() {
        return CladataID;
    }

    public void setCladataID(int cladataID) {
        CladataID = cladataID;
    }

    public String getDatatype() {
        return Datatype;
    }

    public void setDatatype(String datatype) {
        Datatype = datatype;
    }

    public String getDatalink() {
        return datalink;
    }

    public void setDatalink(String datalink) {
        this.datalink = datalink;
    }
}
