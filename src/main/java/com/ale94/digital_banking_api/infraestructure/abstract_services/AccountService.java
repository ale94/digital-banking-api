package com.ale94.digital_banking_api.infraestructure.abstract_services;

import java.math.BigDecimal;

import com.ale94.digital_banking_api.api.models.requests.TransferRequest;
import com.ale94.digital_banking_api.domain.entities.AccountEntity;

public interface AccountService {

    AccountEntity balance(String accountNumber);

    void transfer(TransferRequest request);

    void deposit(String accountNumber, BigDecimal amount);

}
