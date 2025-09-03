package com.ale94.digital_banking_api.api.models.requests;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class TransferRequest implements Serializable {

    @Size(min = 18, max = 20, message = "The size must be between 18 and 20 characters long")
    @NotBlank(message = "The fromAccountNumber is required")
    private String fromAccountNumber;
    @Size(min = 18, max = 20, message = "The size must be between 18 and 20 characters long")
    @NotBlank(message = "The toAccountNumber is required")
    private String toAccountNumber;
    @NotNull(message = "Amount cannot be null")
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    @DecimalMax(value = "999999.99", message = "Amount cannot exceed 999999.99")
    private BigDecimal amount;
    private String description;
}
