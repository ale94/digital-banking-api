package com.ale94.digital_banking_api.infraestructure.abstract_services;

import java.util.List;

import com.ale94.digital_banking_api.api.models.responses.TransactionResponse;

public interface TransactionService {

    List<TransactionResponse> getAllTransactions(String accountNumber);
}
