package com.ale94.digital_banking_api.infraestructure.abstract_services;

import java.util.List;

import com.ale94.digital_banking_api.api.models.requests.UserEditRequest;
import com.ale94.digital_banking_api.api.models.requests.UserRequest;
import com.ale94.digital_banking_api.api.models.responses.UserResponse;

public interface UserService {

    List<UserResponse> findAll();

    UserResponse findById(Long id);

    UserResponse create(UserRequest request);

    UserResponse update(Long id, UserEditRequest request);

    void changePassword(Long id, String Password);

    void lock(Long id);

    void unlock(Long id);

    void delete(Long id);

}
