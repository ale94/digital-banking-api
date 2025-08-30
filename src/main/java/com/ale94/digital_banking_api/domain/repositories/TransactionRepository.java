package com.ale94.digital_banking_api.domain.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ale94.digital_banking_api.domain.entities.TransactionEntity;

public interface TransactionRepository extends JpaRepository<TransactionEntity, String> {
    
    Optional<TransactionEntity> findByOperationNumber(String operationNumber);
}
