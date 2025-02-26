package com.ale94.digital_banking_api.domain.repositories;

import com.ale94.digital_banking_api.domain.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
