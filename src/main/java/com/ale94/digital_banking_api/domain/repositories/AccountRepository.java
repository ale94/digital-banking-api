package com.ale94.digital_banking_api.domain.repositories;

import com.ale94.digital_banking_api.domain.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountEntity, String> {

    Optional<AccountEntity> findByAccountNumber(String accountNumber);

}
