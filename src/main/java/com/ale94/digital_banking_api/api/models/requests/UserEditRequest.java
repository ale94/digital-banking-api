package com.ale94.digital_banking_api.api.models.requests;

import java.io.Serializable;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserEditRequest implements Serializable {

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    private String email;
    @Size(min = 10, max = 12, message = "The size must be between 10 and 12 characters long")
    @NotBlank(message = "The phone is required")
    private String phone;

}
