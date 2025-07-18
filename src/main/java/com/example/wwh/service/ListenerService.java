package com.example.wwh.service;

import com.example.wwh.Mapper.UserMapper;
import com.example.wwh.pojo.Listener;
import com.example.wwh.pojo.MultiUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ListenerService implements UserDetailsService {

    @Autowired
    private UserMapper listenerRepo; // 独立的管理员表DAO

    @Override
    public UserDetails loadUserByUsername(String email) {
        Listener listener = listenerRepo.findListenerByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Admin not found"));


        return new MultiUserDetails(
                listener.getMail(),
                listener.getPassword(),
                "LISTENER"// 标识角色类型
//                listener.getPermissions().stream()
//                        .map(SimpleGrantedAuthority::new)
//                        .collect(Collectors.toList())
        );
    }
}
