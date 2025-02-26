package com.ale94.digital_banking_api.domain.repositories;

import com.ale94.digital_banking_api.domain.entities.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionEntity, String> {
}
