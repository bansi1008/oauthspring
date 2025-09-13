package com.example.ServiceLayer;
import java.util.List;
import com.example.Model.Cart;
import com.example.Model.Order;
import com.example.Model.Orderitem;
import com.example.Repository.Cartrepo;
import com.example.Repository.Productrepo;
import com.example.Repository.Orderrepo;
import com.example.Repository.Orderitemrepo;
import com.example.Model.Product;



import com.example.Model.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;



@Service
public class Orderlayer {
    @Autowired
    private Cartrepo cartrepo;

    @Autowired
    private Orderrepo orderrepo;

    @Autowired
    private Productrepo productrepo;

    @Autowired
    private Orderitemrepo orderitemrepo;
@Transactional(isolation = Isolation.READ_COMMITTED)
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

                for(Cart item:cartitem){
        Product p=productrepo.findById(item.getProduct().getId()).orElseThrow(()->new RuntimeException("Product not found"));
        

          Orderitem o= Orderitem.builder()
          .quantity(item.getQuantity())
          .price(item.getProduct().getPrice())
          .product(item.getProduct())
          .order(order)
          .build();
            orderitemrepo.save(o);

            p.setStock(p.getStock()-item.getQuantity());
            productrepo.save(p);

                }
            cartrepo.deleteAll(cartitem);
              
        
        }

    

    
}
