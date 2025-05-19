package com.example.E_commerce.service.security.service;


import com.example.E_commerce.dto.LoginRequestDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTservice {
    @Autowired
    ApplicationContext context;
    public   boolean isAuthenticated(LoginRequestDTO loginRequestDTO){
        var authenticationManager= context.getBean(AuthenticationManager.class);
        Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO
                .getUserName(),loginRequestDTO.getPassword()));
        return authentication.isAuthenticated();
    }
    @Value("${jwt.secret}")
    private String secretkey;
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .addClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 100 * 60 * 60*60)) // e.g. 3600000 for 1 hour
                .signWith(getKey())
                .compact();
    }

    private SecretKey getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretkey);
        return Keys.hmacShaKeyFor(keyBytes);
    }


}
