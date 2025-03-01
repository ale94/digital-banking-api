package com.ale94.digital_banking_api.infraestructure.services;

import com.ale94.digital_banking_api.api.models.requests.UserRequest;
import com.ale94.digital_banking_api.api.models.responses.AccountResponse;
import com.ale94.digital_banking_api.api.models.responses.UserResponse;
import com.ale94.digital_banking_api.domain.entities.AccountEntity;
import com.ale94.digital_banking_api.domain.entities.UserEntity;
import com.ale94.digital_banking_api.domain.repositories.AccountRepository;
import com.ale94.digital_banking_api.domain.repositories.UserRepository;
import com.ale94.digital_banking_api.infraestructure.abstract_services.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    @Override
    public UserResponse create(UserRequest request) {

        var userToPersist = UserEntity.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .creationDate(LocalDateTime.now())
                .identityDocument(request.getIdentityDocument())
                .username(request.getUsername())
                .password(request.getPassword())
                .build();

        var account = AccountEntity.builder()
                .accountNumber(accountNumberGenerator())
                .cbu(cbuGenerator())
                .alias(aliasGenerator(request))
                .balance(BigDecimal.valueOf(0))
                .build();

        var accountPersisted = this.accountRepository.save(account);
        userToPersist.setAccount(accountPersisted);
        var userPersisted = this.userRepository.save(userToPersist);
        log.info("User saved with id {}", userPersisted.getId());

        return this.entityToResponse(userPersisted);
    }

    private String accountNumberGenerator() {
        StringBuilder prefix = new StringBuilder("BANK" + LocalDateTime.now().getYear());
        for (int i = 0; i < 10; i++) {
            Random aleatorio = new Random();
            prefix.append(aleatorio.nextInt(9));
        }
        return prefix.toString();
    }

    private String cbuGenerator() {
        StringBuilder prefix = new StringBuilder();
        for (int i = 0; i < 23; i++) {
            Random aleatorio = new Random();
            prefix.append(aleatorio.nextInt(9));
        }
        return prefix.toString();
    }

    private String aliasGenerator(UserRequest request) {
        StringBuilder prefix = new StringBuilder();
        prefix.append(request.getName()).append(".").append("BANK.").append(LocalDateTime.now().getYear());
        return prefix.toString();
    }

    private UserResponse entityToResponse(UserEntity entity) {
        var response = new UserResponse();
        BeanUtils.copyProperties(entity, response);
        var accountResponse = new AccountResponse();
        BeanUtils.copyProperties(entity.getAccount(), accountResponse);
        response.setAccountResponse(accountResponse);
        return response;
    }

}
