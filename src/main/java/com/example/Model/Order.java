package com.example.Model;

import java.time.LocalDateTime;

import org.hibernate.annotations.ManyToAny;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Table(name="orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder



public class Order {
    @jakarta.persistence.Id
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private Double totalAmount;
    private String status;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private Signup user;  

    @OneToMany(mappedBy="order", cascade=CascadeType.ALL)
    private java.util.List<Orderitem> orderItems;

  private LocalDateTime createdAt = LocalDateTime.now();

}
