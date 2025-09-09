package com.example.Controller;
import com.example.ServiceLayer.Cartlayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

import com.example.DTO.Cartdto;


@RestController
public class Cartc {
    @Autowired
    private Cartlayer Cartlayer;
@PostMapping("/addtocart")
public String addtocart(@RequestBody Cartdto cartdto, Authentication authentication){
    Long userId = Long.valueOf(authentication.getPrincipal().toString());

    Cartlayer.addtocart(cartdto,userId);
    return "item added to cart";
    
}
}
