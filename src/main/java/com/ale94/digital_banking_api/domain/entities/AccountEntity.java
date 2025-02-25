package com.ale94.digital_banking_api.domain.entities;

import com.ale94.digital_banking_api.util.enums.TypeAccount;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity(name = "account")
public class AccountEntity implements Serializable {

    @Id
    private String accountNumber;
    private String cbu;
    private String alias;
    private TypeAccount typeAccount;
    private BigDecimal balance;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;


    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true,
            mappedBy = "account"
    )
    private TransactionEntity transactions;
}
