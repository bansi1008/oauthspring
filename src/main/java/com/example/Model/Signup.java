package com.example.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
@Entity
@Table(name="users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Signup {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String name;
private String password;
private String email;
private String role;
private boolean oauth;
    
}
