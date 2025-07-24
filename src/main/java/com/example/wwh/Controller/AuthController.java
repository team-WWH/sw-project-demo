package com.example.wwh.Controller;

import com.example.wwh.Data.LoginRequest;
import com.example.wwh.Data.RegisterRequest;
import com.example.wwh.pojo.UserType;
import com.example.wwh.service.MultiRoleAuthService;
import com.example.wwh.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired private MultiRoleAuthService authService;
    @Autowired
    private UserService userService;
    @Autowired private PasswordEncoder passwordEncoder;
    @PostMapping("/login")
    public ResponseEntity<?> Userlogin(@RequestBody LoginRequest request) {
        System.out.println("chenggongdiaoyong");
        String token = authService.authenticate(request);
        return ResponseEntity.ok(Map.of("token", token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> UserRegister(@RequestBody RegisterRequest request){
        System.out.println(request.getUname());
        System.out.println(request.getSex());
        System.out.println(request.getRole());
        System.out.println(request.getMail());
        System.out.println(request.getPhone());
        System.out.println(request.getPassword());
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        if(Objects.equals(request.getRole(), "Listener")){
            userService.addListener(request);
        }else if(Objects.equals(request.getRole(), "Speaker")){
            userService.addSpeaker(request);
        }else if(Objects.equals(request.getRole(), "Organizer")){
            userService.addOrganizer(request);
        }else{
            return ResponseEntity.badRequest().body("身份错误");
        }

        return ResponseEntity.ok("注册成功");
    }
}




