package com.example.Repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Model.Cart;
import java.util.List;

public interface Cartrepo extends JpaRepository<Cart, Long> {

    List<Cart> findByUser_Id(Long id);
    
}
