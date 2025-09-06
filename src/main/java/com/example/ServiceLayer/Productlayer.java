package com.example.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DTO.Productdto;
import com.example.Model.Product;
import com.example.Repository.Productrepo;
import java.util.List;

@Service
public class Productlayer {
    @Autowired
    private Productrepo productrepo;

    public void addproduct(Productdto productdto) {
        System.out.println("Adding product: " + productdto.getName() + ", " + productdto.getDescription() + ", " + productdto.getPrice() + ", " + productdto.getStock());
        Product p = Product.builder()
                .name(productdto.getName())
                .description(productdto.getDescription())
                .price(productdto.getPrice())
                .stock(productdto.getStock())
                .build();
        productrepo.save(p);
       
    }
  public List<Product> searchProducts(String keyword) {
        return productrepo.searchByName(keyword);
    }
    
}
