package com.ale94.digital_banking_api.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity(name = "transaction")
public class TransactionEntity implements Serializable {

    @Id
    private String operationNumber;
    private String destinationAccount;
    private BigDecimal amount;
    private String description;
    private LocalDateTime date;
    private String notification;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source_account")
    private AccountEntity account;

}
