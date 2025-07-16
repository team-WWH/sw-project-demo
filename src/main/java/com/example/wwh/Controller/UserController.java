package com.example.wwh.Controller;

import com.example.wwh.Config.JwtTokenUtil;
import com.example.wwh.pojo.Listener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller("/login")
public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/Listenerlogin")
    public ResponseEntity<String> ListenerLogin(String username,String password){
        try {
            // 1. 创建认证令牌
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            // 2. 获取用户详情（包含加密密码）
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            // 3. 生成JWT
            String token = jwtTokenUtil.generateToken(userDetails);

            return ResponseEntity.ok(token);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("用户名或密码错误");
        }

    }
    @PostMapping("/Speecherlgin")
    public ResponseEntity<String> SpeecherLogin(String username ,String password){

        try {
            // 1. 创建认证令牌
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            // 2. 获取用户详情（包含加密密码）
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            // 3. 生成JWT
            String token = jwtTokenUtil.generateToken(userDetails);

            return ResponseEntity.ok(token);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("用户名或密码错误");
        }
    }
    @PostMapping("/Organizerlogin")
    public ResponseEntity<String> OrganizerLogin(String username,String password){
        try {
            // 1. 创建认证令牌
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            // 2. 获取用户详情（包含加密密码）
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            // 3. 生成JWT
            String token = jwtTokenUtil.generateToken(userDetails);

            return ResponseEntity.ok(token);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("用户名或密码错误");
        }
    }
}
