package com.ale94.digital_banking_api.api.models.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserRequest implements Serializable {
    private String name;
    private String email;
    private String phone;
    private String identityDocument;
    private String username;
    private String password;
}
