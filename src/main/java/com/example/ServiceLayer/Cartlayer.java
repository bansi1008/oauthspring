package com.example.ServiceLayer;
import com.example.Controller.Cartc;
import com.example.DTO.Cartdto;
import com.example.Model.Cart;
import com.example.Model.Product;
import com.example.Model.Signup;
import com.example.Repository.Cartrepo;
import com.example.Repository.Productrepo;
import com.example.Repository.Userrepo;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Cartlayer {
    @Autowired
    private Cartrepo cartrepo;
    @Autowired
    private Userrepo Userrepo;
    @Autowired
    private Productrepo Productrepo;


   public void addtocart(Cartdto cartdto) {
    Signup user = Signup.builder().id(cartdto.getUserId()).build();
    Product product = Product.builder().id(cartdto.getProductId()).build();

    Cart cart = Cart.builder()
            .user(user)
            .product(product)
            .quantity(cartdto.getQuantity())
            .build();

    cartrepo.save(cart);
}


    
}
