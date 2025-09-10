package com.example.Security;

import com.example.Utility.Jwtfilter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class Security {

  @Autowired
  private Jwtfilter jwtfilter;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
      .authorizeHttpRequests(auth -> auth
      .requestMatchers("/login","/signup","/addproduct","/products/search","/addtocart","/mee","/checkout").permitAll()
      .requestMatchers("/admin").hasRole("ADMIN")
      .requestMatchers("/user").hasAnyRole("USER")
        .anyRequest().authenticated()
      )
      .addFilterBefore(jwtfilter, UsernamePasswordAuthenticationFilter.class)
      .httpBasic(withDefaults())
      .csrf(csrf -> csrf.disable())
      .oauth2Login(withDefaults());
    return http.build();
  }
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
    
}
