package com.example.wwh.service;

import com.example.wwh.Config.JwtTokenProvider;
import com.example.wwh.Data.LoginRequest;
import com.example.wwh.pojo.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MultiRoleAuthService {

    @Autowired
    private ListenerService listenerService;      // 普通用户服务
    @Autowired private SpeakerService speakerService;  // 管理员服务
    @Autowired private OrganizerService organizerService;// 供应商服务
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private JwtTokenProvider jwtTokenProvider;

    public String authenticate(LoginRequest request) {
        UserDetails userDetails = loadUserByRole(
                request.getUsername(),
                request.getUserType()
        );

        // 密码验证
        if (!passwordEncoder.matches(request.getPassword(), userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        // 生成JWT（包含角色类型）
        return jwtTokenProvider.createToken(
                request.getUsername(),
                request.getUserType(),
                userDetails.getAuthorities()
        );
    }

    public UserDetails loadUserByRole(String username, UserType userType) {
        return switch (userType) {
            case LISTENER  -> listenerService.loadUserByUsername(username);
            case SPEAKER -> speakerService.loadUserByUsername(username);
            case ORGANIZER -> organizerService.loadUserByUsername(username);
            default -> throw new UsernameNotFoundException("Unknown user type");
        };
    }

}