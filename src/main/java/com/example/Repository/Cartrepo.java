package com.example.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Model.Cart;

public interface Cartrepo extends JpaRepository<Cart, Long> {
    
}
