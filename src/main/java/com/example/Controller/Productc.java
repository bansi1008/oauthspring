package com.example.Controller;
import com.example.ServiceLayer.Productlayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.DTO.Productdto;
import com.example.Model.Product;
import java.util.List;

@RestController
public class Productc {
    @Autowired
    private Productlayer productlayer;

     @PostMapping("/addproduct")
    public String addproduct(@RequestBody Productdto productdto) {
        productlayer.addproduct(productdto);

        return "Product added successfully!";
    }

     @GetMapping("/products/search")
    public List<Product> searchProducts(@RequestParam String keyword) {
        return productlayer.searchProducts(keyword);
    }

    
}
