package com.ale94.digital_banking_api.domain.repositories;

import com.ale94.digital_banking_api.domain.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, String> {
}
