package com.ale94.digital_banking_api.api.models.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserResponse implements Serializable {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private LocalDateTime creationDate;
    private String identityDocument;
    private String username;
    private Boolean lock;
    private AccountResponse accountResponse;
}
