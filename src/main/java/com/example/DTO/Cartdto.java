package com.example.DTO;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Cartdto {
    private Long userId;
    private Long productId;
    private Integer quantity;
    
}
