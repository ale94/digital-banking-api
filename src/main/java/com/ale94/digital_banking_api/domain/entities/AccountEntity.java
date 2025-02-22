package com.ale94.digital_banking_api.domain.entities;

import com.ale94.digital_banking_api.util.enums.TypeAccount;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
@Entity(name = "account")
public class AccountEntity implements Serializable {

    private String accountNumber;
    private String cbu;
    private String alias;
    private TypeAccount typeAccount;
    private BigDecimal balance;

    @OneToOne
    @JoinColumn(name = "user_id",
            referencedColumnName = "id",
            unique = true)
    private UserEntity user;
}
