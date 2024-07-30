package com.SprintHub.scrum_project_manager.service;

import com.SprintHub.scrum_project_manager.model.Users;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.SneakyThrows;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTService {
    private static final String SECRET_KEY = "2208153513d489a979265575aa0dfd5bed7bd9588981bb60f4a5e19d02614433";
    private static final long EXPIRATION_TIME = 60 * 60 * 1000;

    public String getToken(Users users){
        return getToken(new HashMap<>(), users);
    }

    private String getToken(Map<String, Object> extraClaims, Users users) {
        return Jwts.builder().
                setClaims(extraClaims).
                setSubject(users.getTokenUser()).
                setIssuedAt(new Date(System.currentTimeMillis())).
                setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)).
                signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes())).compact();
    }

    @SneakyThrows
    public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(SECRET_KEY.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String email = claims.getSubject();
            return new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());
        } catch (JwtException e) {
            throw new AuthenticationException(e.getMessage());
        }
    }
}
