package com.ale94.digital_banking_api.infraestructure.abstract_services;

import com.ale94.digital_banking_api.api.models.requests.UserRequest;
import com.ale94.digital_banking_api.api.models.responses.UserResponse;

public interface UserService {

    UserResponse create(UserRequest request);

}
