package com.ale94.digital_banking_api.domain.repositories;

import com.ale94.digital_banking_api.domain.entities.UserEntity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);
    
}
