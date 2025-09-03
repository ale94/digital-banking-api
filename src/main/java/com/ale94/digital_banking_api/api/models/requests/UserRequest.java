package com.ale94.digital_banking_api.api.models.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserRequest implements Serializable {

    @Size(min = 6, max = 12, message = "The size must be between 6 and 12 characters long")
    @NotBlank(message = "The name is required")
    private String name;
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    private String email;
    @Size(min = 10, max = 12, message = "The size must be between 10 and 12 characters long")
    @NotBlank(message = "The phone is required")
    private String phone;
    @Size(min = 8, max = 10, message = "The size must be between 8 and 10 characters long")
    @NotBlank(message = "The identityDoc is required")
    private String identityDoc;
    @Size(min = 6, max = 12, message = "The size must be between 6 and 12 characters long")
    @NotBlank(message = "The username is required")
    private String username;
    @Size(min = 8, max = 12, message = "The size must be between 8 and 12 characters long")
    @NotBlank(message = "The password is required")
    private String password;
}
