package com.example.Model;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Column;
import lombok.*;

@Entity
@Table(name="carts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class Cart {
    @Id
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
   
    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name="user_id", nullable=false)
    private Signup user;

    @ManyToOne(fetch = FetchType.LAZY)    
    @JoinColumn(name="product_id", nullable=false)
    private Product product;

    private Integer quantity;

    @Column(name="created_at")
    private LocalDateTime createdAt = LocalDateTime.now();



}
