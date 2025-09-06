package com.example.Controller;
import com.example.ServiceLayer.Cartlayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.DTO.Cartdto;


@RestController
public class Cartc {
    @Autowired
    private Cartlayer Cartlayer;
@PostMapping("/addtocart")
public String addtocart(@RequestBody Cartdto cartdto){
    System.out.println("Received cartdto: " + cartdto);

    Cartlayer.addtocart(cartdto);
    return "item added to cart";
    
}
}
