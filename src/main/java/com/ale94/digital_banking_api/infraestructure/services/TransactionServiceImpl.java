package com.ale94.digital_banking_api.infraestructure.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ale94.digital_banking_api.api.models.responses.TransactionResponse;
import com.ale94.digital_banking_api.domain.entities.TransactionEntity;
import com.ale94.digital_banking_api.domain.repositories.AccountRepository;
import com.ale94.digital_banking_api.infraestructure.abstract_services.TransactionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    private final AccountRepository accountRepository;

    @Override
    public List<TransactionResponse> getAllTransactions(String accountNumber) {
        var account = this.accountRepository.findByAccountNumber(accountNumber).orElseThrow();
        var transactions = account.getTransactions();
        return transactions.stream().map(this::entityToResponse).toList();
    }

    private TransactionResponse entityToResponse(TransactionEntity entity) {
        var response = new TransactionResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

}
