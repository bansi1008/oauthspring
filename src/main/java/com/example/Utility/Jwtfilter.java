package com.example.Utility;

import org.springframework.stereotype.Component;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;

@Component
public class Jwtfilter extends OncePerRequestFilter {
    @Value("${jwt.secret}")
    private  String secret;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

                String token = null;
                if(request.getCookies() != null) {
                  token = java.util.Arrays.stream(request.getCookies())
                    .filter(c -> c.getName().equals("learning_token"))
                    .findFirst()
                    .map(c -> c.getValue())
                    .orElse(null);
        }
        if (token != null &&SecurityContextHolder.getContext().getAuthentication() == null) {
            try {
                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(secret.getBytes())
                        .build()
                        .parseClaimsJws(token)
                        .getBody();

                String email = claims.getSubject();
                String name = claims.get("name", String.class);
               String role = claims.get("roles", String.class);
               if(role == null) role = "USER";

                Collection<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_"+role));

                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(email, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authToken);
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }
        
        filterChain.doFilter(request, response);
    }
    
}
