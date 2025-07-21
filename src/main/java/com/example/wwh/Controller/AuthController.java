package com.example.wwh.Controller;

import com.example.wwh.Data.LoginRequest;
import com.example.wwh.pojo.UserType;
import com.example.wwh.service.MultiRoleAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired private MultiRoleAuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> Userlogin(@RequestBody LoginRequest request) {
        System.out.println("chenggongdiaoyong");
        String token = authService.authenticate(request);
        return ResponseEntity.ok(Map.of("token", token));
    }
}




