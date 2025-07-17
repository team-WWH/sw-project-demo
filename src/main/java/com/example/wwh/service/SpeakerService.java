package com.example.wwh.service;

import com.example.wwh.Mapper.UserMapper;
import com.example.wwh.pojo.Listener;
import com.example.wwh.pojo.MultiUserDetails;
import com.example.wwh.pojo.Speaker;
import com.example.wwh.pojo.Speech;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class SpeakerService implements UserDetailsService {

    @Autowired
    private UserMapper speakerRepo; // 独立的管理员表DAO

    @Override
    public UserDetails loadUserByUsername(String email) {
        Speaker speaker = speakerRepo.findSpeakerByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Admin not found"));

        return new MultiUserDetails(
                speaker.getSmail(),
                speaker.getSpassword(),
                "SPEAKER"// 标识角色类型
//                speaker.getPermissions().stream()
//                        .map(SimpleGrantedAuthority::new)
//                        .collect(Collectors.toList())
        );
    }
}