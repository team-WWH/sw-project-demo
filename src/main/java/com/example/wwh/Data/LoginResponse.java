package com.example.wwh.Data;

import com.example.wwh.pojo.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private UserType userType;
    private Long userId;
    private String username;
}
