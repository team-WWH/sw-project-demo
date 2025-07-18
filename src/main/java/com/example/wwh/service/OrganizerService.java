package com.example.wwh.service;

import com.example.wwh.Mapper.UserMapper;
import com.example.wwh.pojo.Listener;
import com.example.wwh.pojo.MultiUserDetails;
import com.example.wwh.pojo.Organizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class OrganizerService implements UserDetailsService {

    @Autowired
    private UserMapper listenerRepo; // 独立的管理员表DAO

    @Override
    public UserDetails loadUserByUsername(String email) {
        Organizer organizer = listenerRepo.findOrganizerByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Admin not found"));
        return new MultiUserDetails(
                organizer.getOmail(),
                organizer.getOpassword(),
                "ORGANIZER"// 标识角色类型
//                organizer.getPermissions().stream()
//                        .map(SimpleGrantedAuthority::new)
//                        .collect(Collectors.toList())
        );
    }
}