package com.example.Repository;
import com.example.Model.Order;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Orderrepo extends JpaRepository<Order , Long> {
   Optional<Order> findById(Long id);
    
}
