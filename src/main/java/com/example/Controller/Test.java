package com.example.Controller;

import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.annotation.AuthenticationPrincipal;


@RestController

public class Test {

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

    
}
