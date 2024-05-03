package com.example.JwtToken.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.JwtToken.entity.UserEntity;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long> {

    UserEntity findOneByEmailIdIgnoreCaseAndPassword(String emailId, String password);
    
}
