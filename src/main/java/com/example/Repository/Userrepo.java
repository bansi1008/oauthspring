package com.example.Repository;
import com.example.Model.Signup;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface Userrepo extends  JpaRepository<Signup , Long> {
   Optional<Signup> findByEmail(String email);

}
