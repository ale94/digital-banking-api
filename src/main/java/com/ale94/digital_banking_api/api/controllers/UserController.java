package com.ale94.digital_banking_api.api.controllers;

import com.ale94.digital_banking_api.api.models.requests.UserRequest;
import com.ale94.digital_banking_api.api.models.responses.UserResponse;
import com.ale94.digital_banking_api.infraestructure.abstract_services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.create(request));
    }

}
