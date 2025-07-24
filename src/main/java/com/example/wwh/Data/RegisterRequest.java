package com.example.wwh.Data;

import lombok.Data;

@Data
public class RegisterRequest {
   private String Uname;
   private String Password;
  private   String Mail;
  private   String Phone;
   private Integer Sex;
}
