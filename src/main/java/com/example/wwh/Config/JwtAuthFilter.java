package com.example.wwh.Config;

import com.example.wwh.pojo.UserType;
import com.example.wwh.service.MultiRoleAuthService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    @Autowired
    @Lazy
    private MultiRoleAuthService authService;
    @Autowired private JwtTokenProvider jwtProvider;
    @Autowired
    public JwtAuthFilter(@Lazy MultiRoleAuthService authService, JwtTokenProvider jwtProvider) {
        this.authService = authService;
        this.jwtProvider = jwtProvider;
    }



    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws ServletException, IOException {

        String token = resolveToken(req);
        if (token != null && jwtProvider.validateToken(token)) {
            // 从Token中获取角色类型
            UserType roleType = jwtProvider.getRoleTypeFromToken(token);
            String username = jwtProvider.getUsernameFromToken(token);
            System.out.println(roleType);
            System.out.println(username);
            // 根据角色类型动态加载用户
            UserDetails userDetails = authService.loadUserByRole(username, roleType);
            System.out.println(userDetails.getUsername());
            System.out.println(userDetails.getAuthorities());

            // 构建Authentication对象
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities()
            );
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        chain.doFilter(req, res);
    }

    private String resolveToken(HttpServletRequest request) {
        // 1. 从Authorization头获取Token
        String bearerToken = request.getHeader("Authorization");

        // 2. 验证并提取Token
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            // 移除"Bearer "前缀，返回纯Token
            return bearerToken.substring(7);
        }

        // 3. 备选方案：从URL参数获取Token
        String tokenParam = request.getParameter("token");
        if (StringUtils.hasText(tokenParam)) {
            return tokenParam;
        }

        // 4. 备选方案：从Cookie获取Token
        Cookie[] cookies = request.getCookies();
        System.out.println(cookies);
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName());
                if ("AUTH_TOKEN".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        System.out.println("没有token");
        // 5. 未找到有效Token
        return null;
    }
}