package com.example.Controller;
import com.example.Repository.Userrepo;
import com.example.Model.Signup;

import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;


@RestController

public class Test {
    @Autowired
    private Userrepo userRepository;
   


    @GetMapping("/test")
    public String test() {
        return "Hello, World!";
    }

    @GetMapping("/me")
    public String userInfo(@AuthenticationPrincipal OidcUser user) {
        return "Hello, " + user.getFullName() + " (" + user.getEmail() + ")";
    }

    @GetMapping("/admin")
    public String adminInfo() {
        return "Hello, Admin!";
    }
    @GetMapping("/user")   
    public String user() {
        return "Hello, User!";
    }
    @GetMapping("/public")
    public String publicEndpoint() {
        return "Hello, Public!";
    }

    @GetMapping("/mee")
    public String mee(Authentication authentication) {
        String userId = (String) authentication.getPrincipal();

        Long userIdd = Long.valueOf(authentication.getPrincipal().toString());
        Signup s = userRepository.findById(userIdd).orElse(null);
        
        
            String role = authentication.getAuthorities().toString();

   

        return "Hello, " +  s.getName() +   " your user id is"+ userId + " (" +role + ")";
    }

    
}
