package com.example.ServiceLayer;
import java.util.List;
import com.example.Model.Cart;
import com.example.Model.Order;
import com.example.Repository.Cartrepo;

import com.example.Repository.Orderrepo;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Orderlayer {
    @Autowired
    private Cartrepo cartrepo;

    @Autowired
    private Orderrepo orderrepo;

    public void placeOrder(Long userId) {
        
    
        System.out.println("Placing order for user ID: " + userId);
        List<Cart> cartitem=cartrepo.findByUser_Id(userId);
        if(cartitem.isEmpty()){
            throw new RuntimeException("Cart is empty. Cannot place order.");
        }
        double totalAmount = 0;
        for (Cart item : cartitem) {
            double itemTotal = item.getProduct().getPrice() * item.getQuantity();
            totalAmount += itemTotal;
        }
        System.out.println("Total Amount: " + totalAmount);
        Order order = Order.builder()
               
                .user(cartitem.get(0).getUser())
                .totalAmount(totalAmount)
                .status("PLACED")
                .build(); 

                orderrepo.save(order);
     
    }

    
}
