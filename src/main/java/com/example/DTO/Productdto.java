package com.example.DTO;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Productdto {
    
    private String name;
    private String description;
    private Double price;
    private int stock;

    
}
