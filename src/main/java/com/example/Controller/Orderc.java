package com.example.Controller;
import com.example.ServiceLayer.Orderlayer;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
@RestController

public class Orderc {

    @Autowired
    private Orderlayer orderlayer;
   
 @PostMapping("/checkout")
    public String placeOrder(Authentication authentication) {
        Long userId = Long.valueOf(authentication.getPrincipal().toString());
         orderlayer.placeOrder(userId);
        
        return "Order placed successfully!";
    }

    
}
