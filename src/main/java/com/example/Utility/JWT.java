package com.example.Utility;
import com.example.Model.Signup;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

import java.security.Key;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class JWT {
    @Value("${jwt.secret}")
   private  String secret;
    @Value("${jwt.expiration}")
    private long expiration;

    private Key key;

    @PostConstruct
    public void init() {
        key = Keys.hmacShaKeyFor(secret.getBytes());
    }
    

    public String generateToken(Signup s) {
        System.out.println("your role is "+s.getRole());
        return Jwts.builder()
                .setSubject(s.getEmail())
                .claim("name", s.getName())
                .claim("email", s.getEmail())
                .claim("roles", s.getRole())
                .setExpiration(new java.util.Date(System.currentTimeMillis() + expiration))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}
