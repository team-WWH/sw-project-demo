package com.example.wwh.Config;



import com.example.wwh.pojo.UserType;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long validityInMilliseconds;

    private Key key;

    @PostConstruct
    protected void init() {
        // 将密钥转换为安全的Key对象
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String createToken(String username, UserType userType, Collection<? extends GrantedAuthority> authorities) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("roleType", userType.name());
        claims.put("auth", authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()));

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // 验证Token是否有效
    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);

            // 检查Token是否过期
            return !claims.getBody().getExpiration().before(new Date());
        } catch (ExpiredJwtException ex) {
            // Token过期
            //.error("JWT expired: {}", ex.getMessage());
        } catch (UnsupportedJwtException | MalformedJwtException | SignatureException ex) {
            // 无效的Token
           // log.error("Invalid JWT: {}", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            // 空或空白的Token
            //log.error("JWT claims string is empty");
        }
        return false;
    }

    // 从Token中获取用户名
    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // 从Token中获取角色类型
    public UserType getRoleTypeFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return UserType.valueOf((String) claims.get("roleType"));
    }
}