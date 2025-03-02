package com.ale94.digital_banking_api.infraestructure.services;

import com.ale94.digital_banking_api.domain.entities.AccountEntity;
import com.ale94.digital_banking_api.domain.repositories.AccountRepository;
import com.ale94.digital_banking_api.infraestructure.abstract_services.AccountService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public AccountEntity getBalance(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber).orElseThrow();
    }

}
