package com.ale94.digital_banking_api.infraestructure.services;

import com.ale94.digital_banking_api.api.models.requests.TransferRequest;
import com.ale94.digital_banking_api.domain.entities.AccountEntity;
import com.ale94.digital_banking_api.domain.repositories.AccountRepository;
import com.ale94.digital_banking_api.infraestructure.abstract_services.AccountService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

    @Override
    public void deposit(TransferRequest request) {

        var fromAccount = accountRepository.findByAccountNumber(request.getFromAccountNumber())
                .orElseThrow();
        var toAccount = accountRepository.findByAccountNumber(request.getToAccountNumber())
                .orElseThrow();

        var userFrom = fromAccount.getUser();
        var userTo = toAccount.getUser();

        // Validar que la cuenta origen no esté bloqueada
        if (userFrom.isLock()) {
            throw new IllegalStateException("La cuenta origen está bloqueada.");
        }

        // Validar que tenga fondos suficientes
        if (fromAccount.getBalance().compareTo(request.getAmount()) < 0) {
            throw new IllegalArgumentException("Fondos insuficientes en cuenta origen.");
        }

        // Descontar de la cuenta origen
        fromAccount.setBalance(fromAccount.getBalance().subtract(request.getAmount()));

        // Acreditar en la cuenta destino (si no está bloqueada)
        if (!userTo.isLock()) {
            toAccount.setBalance(toAccount.getBalance().add(request.getAmount()));
        } else {
            throw new IllegalStateException("La cuenta destino está bloqueada.");
        }

        // Guardar cambios
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
    }

}
