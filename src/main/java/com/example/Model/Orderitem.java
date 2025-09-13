package com.example.Model;

import java.math.BigDecimal;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Table(name="orderitems")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Orderitem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer  quantity;
    private Double price;

    @ManyToOne
    @JoinColumn(name="order_id", nullable=false)
    private Order order;

    @ManyToOne
    @JoinColumn(name="product_id", nullable=false)
    private Product product;
    
}
