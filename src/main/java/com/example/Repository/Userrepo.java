package com.example.Repository;
import com.example.Model.Signup;
import com.example.DTO.Logindto;
import java.util.Optional;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface Userrepo extends  JpaRepository<Signup , Long> {
       @Query("SELECT  u.password  FROM Signup u WHERE u.email = :email")
           Optional<Logindto> findForLogin(@Param("email") String email);


   Optional<Signup> findById(Long id);
   Optional<Signup> findByEmail(String email);


}
