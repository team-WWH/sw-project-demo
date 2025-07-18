package com.example.wwh.Data;

import com.example.wwh.pojo.UserType;
import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
    UserType userType; // 标识角色类型

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}