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




}
