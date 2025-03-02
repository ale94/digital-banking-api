package com.ale94.digital_banking_api.infraestructure.abstract_services;

import com.ale94.digital_banking_api.domain.entities.AccountEntity;

import java.math.BigDecimal;

public interface AccountService {

    AccountEntity getBalance(String accountNumber);

    void addBalance(String accountNumber, BigDecimal balance);

}

