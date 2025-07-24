package com.example.wwh.pojo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MultiUserDetails implements UserDetails {
    private String username;
    private String password;
    private String roleType;
    private Collection<? extends GrantedAuthority> authorities;
    public MultiUserDetails(String username, String password, String roleType){
        this.password = password;
        this.username = username;
        this.roleType = roleType;
        List<GrantedAuthority> allAuths = new ArrayList<>();
        //allAuths.add(new SimpleGrantedAuthority("ROLE_" + roleType));
        this.authorities = allAuths;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> allAuths = new ArrayList<>();
        allAuths.add(new SimpleGrantedAuthority("ROLE_" + roleType));
        allAuths.addAll(authorities);
        return allAuths;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
}
