package com.ale94.digital_banking_api.infraestructure.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.ale94.digital_banking_api.api.models.requests.UserEditRequest;
import com.ale94.digital_banking_api.api.models.requests.UserRequest;
import com.ale94.digital_banking_api.api.models.responses.UserResponse;
import com.ale94.digital_banking_api.domain.entities.AccountEntity;
import com.ale94.digital_banking_api.domain.entities.UserEntity;
import com.ale94.digital_banking_api.domain.repositories.UserRepository;
import com.ale94.digital_banking_api.infraestructure.abstract_services.UserService;
import com.ale94.digital_banking_api.infraestructure.mappers.UserMapper;
import com.ale94.digital_banking_api.util.exceptions.IdNotFoundException;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponse create(UserRequest request) {

        var accountToPersist = AccountEntity.builder()
                .accountNumber(accountNumberGenerator())
                .cbu(cbuGenerator())
                .alias(aliasGenerator(request))
                .balance(BigDecimal.valueOf(0))
                .build();

        var userToPersist = UserEntity.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .regDate(LocalDateTime.now())
                .identityDoc(request.getIdentityDoc())
                .username(request.getUsername())
                .password(request.getPassword())
                .isLock(false)
                .account(accountToPersist)
                .build();

        accountToPersist.setUser(userToPersist);

        var userPersisted = this.userRepository.save(userToPersist);
        log.info("User saved with id {}", userPersisted.getId());
        return this.userMapper.toUserResponse(userPersisted);
    }

    @Override
    public List<UserResponse> findAll() {
        return this.userRepository.findAll()
                .stream()
                .map(userMapper::toUserResponse)
                .toList();
    }

    @Override
    public UserResponse findById(Long id) {
        var userFromDB = this.userRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("user"));
        return this.userMapper.toUserResponse(userFromDB);
    }

    @Override
    public UserResponse update(Long id, UserEditRequest request) {
        var userToUpdate = this.userRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("user"));
        userToUpdate.setEmail(request.getEmail());
        userToUpdate.setPhone(request.getPhone());
        var userUpdated = this.userRepository.save(userToUpdate);
        log.info("User updated with id {}", userUpdated.getId());
        return this.userMapper.toUserResponse(userUpdated);
    }

    @Override
    public void changePassword(Long id, String password) {
        var userToChangePassword = this.userRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("user"));
        userToChangePassword.setPassword(password);
        this.userRepository.save(userToChangePassword);
    }

    @Override
    public void lock(Long id) {
        var userToLock = this.userRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("user"));
        userToLock.setLock(true);
    }

    @Override
    public void unlock(Long id) {
        var userToUnLock = this.userRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("user"));
        userToUnLock.setLock(false);
    }

    @Override
    public void delete(Long id) {
        var userToDelete = this.userRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("user"));
        this.userRepository.delete(userToDelete);
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

    // private UserResponse entityToResponse(UserEntity entity) {
    //     var response = new UserResponse();
    //     BeanUtils.copyProperties(entity, response);
    //     var accountResponse = new AccountResponse();
    //     BeanUtils.copyProperties(entity.getAccount(), accountResponse);
    //     response.setAccountResponse(accountResponse);
    //     return response;
    // }

}
