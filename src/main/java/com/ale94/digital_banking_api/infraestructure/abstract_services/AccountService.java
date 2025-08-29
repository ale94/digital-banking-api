package com.ale94.digital_banking_api.infraestructure.abstract_services;

import com.ale94.digital_banking_api.api.models.requests.TransferRequest;
import com.ale94.digital_banking_api.domain.entities.AccountEntity;

public interface AccountService {

    AccountEntity getBalance(String accountNumber);

    void transfer(TransferRequest request);

}
