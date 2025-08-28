package com.ale94.digital_banking_api.api.models.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class TransferRequest implements Serializable {
    private String fromAccountNumber;
    private String toAccountNumber;
    private BigDecimal amount;
}
