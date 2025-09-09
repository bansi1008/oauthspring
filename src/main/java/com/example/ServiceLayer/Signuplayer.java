package com.example.ServiceLayer;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import com.example.Repository.Userrepo;
import com.example.DTO.Signupdto;
import com.example.Model.Signup;
import com.example.DTO.Logindto;
import com.example.Utility.JWT;

import org.springframework.stereotype.Service;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;



@Service
public class Signuplayer {
    @Autowired
    private Userrepo userRepository;

    @Autowired
    private JWT jwt;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String signuplayerr(Signupdto signupdto) {

        
         userRepository.findByEmail(signupdto.getEmail()).ifPresent(user -> {
            throw new IllegalArgumentException("Email already in use");
        }); 
        
        String encodedPassword = passwordEncoder.encode(signupdto.getPassword());
        

    Signup s=Signup.builder()
        .name(signupdto.getName())
        .email(signupdto.getEmail())
        .password(encodedPassword)
        .role(signupdto.getRole())
        .oauth(false)
        .build();   
userRepository.save(s);
        String token = jwt.generateToken(s);

        
    return token;

        
        
}
   public String loginlayerr(Logindto logindto) {
   Signup ex= userRepository.findByEmail(logindto.getEmail())
        .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
    
   if(ex!=null){
  
    if (!passwordEncoder.matches( logindto.getPassword(),ex.getPassword())) {
        throw new IllegalArgumentException("Invalid  or password");
    }
} 
    String token = jwt.generateToken(ex);
    return token;  
   }

   public String loginwithgoogle(@AuthenticationPrincipal OidcUser user) {
    Map<String, Object> attributes = user.getAttributes();
    String email = (String) attributes.get("email");
    String name = (String) attributes.get("name");

  Signup ex=  userRepository.findByEmail(email).orElseGet(() -> {
        Signup newUser = Signup.builder()
                .name(name)
                .email(email)
                .password("") 
                .role("USER")
                .oauth(true)
                .build();
        return userRepository.save(newUser);
    });
    if (ex == null) {
        throw new IllegalArgumentException("User not found after Google login");
    }
    String token = jwt.generateToken(ex);
    System.out.println("Generated JWT: " + token);
    return token;
   
    
}
}