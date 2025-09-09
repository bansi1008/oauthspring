package com.example.Controller;
import com.example.Repository.Userrepo;
import com.example.Model.Signup;
import com.example.DTO.Signupdto;
import com.example.DTO.Logindto;
import com.example.ServiceLayer.Signuplayer;
import com.example.Utility.JWT;



import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import java.util.Map;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class Signupc {
  
    @Autowired
    private Signuplayer signuplayer;
   

    @PostMapping("/signup")
    public void signup(@RequestBody Signupdto signupdto,HttpServletResponse response) {
       String token= signuplayer.signuplayerr(signupdto);

       Cookie cookie = new Cookie("learning_token", token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60); 
        cookie.setSecure(true); 
        response.addCookie(cookie);

        
    }
    @PostMapping("/login")
    public String login(@RequestBody Logindto logindto,HttpServletResponse response) {
      String token=  signuplayer.loginlayerr(logindto);
      Cookie cookie = new Cookie("learning_token", token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60); 
        cookie.setSecure(true); 
        response.addCookie(cookie);

        return "User logged in successfully!";
    }

    @GetMapping("/loginwithgoogle")
    public String loginwithgoogle(@AuthenticationPrincipal OidcUser user,HttpServletResponse response) {
      String token=signuplayer.loginwithgoogle(user);

        Cookie cookie = new Cookie("learning_token", token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60); 
        cookie.setSecure(true); 
        response.addCookie(cookie);
        return "User logged in with Google successfully!";
       
    }

    
}
