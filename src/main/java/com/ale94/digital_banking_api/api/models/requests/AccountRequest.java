package com.ale94.digital_banking_api.api.models.requests;

//import com.ale94.digital_banking_api.util.enums.TypeAccount;
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
public class AccountRequest implements Serializable {
    private String accountNumber;
    private String cbu;
    private String alias;
    //private TypeAccount typeAccount;
    private BigDecimal balance;
}
