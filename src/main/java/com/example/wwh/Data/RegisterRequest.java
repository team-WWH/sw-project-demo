package com.example.wwh.Data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RegisterRequest {
    @JsonProperty("Mail")
    private   String Mail;
    @JsonProperty("Password")
    private String Password;
    @JsonProperty("Phone")
    private   String Phone;
    @JsonProperty("Sex")
    private String Sex;
    @JsonProperty("Uname")
   private String Uname;
    @JsonProperty("Role")
    private String Role;


    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        Mail = mail;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getUname() {
        return Uname;
    }

    public void setUname(String uname) {
        Uname = uname;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }
}
