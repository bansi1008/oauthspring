package com.example.Repository;
import com.example.Model.Product;


import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface Productrepo extends JpaRepository<Product , Long> {
    
   Optional<Product> findById(Long id);

   @Query("SELECT p FROM Product p " +
       "WHERE p.name LIKE %:keyword% " +
       "ORDER BY CASE WHEN p.name = :keyword THEN 0 ELSE 1 END, p.name")
   List<Product> searchByName(@Param("keyword") String keyword);


    
}

