package com.ale94.digital_banking_api.api.models.responses;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
public class BaseErrorResponse implements Serializable {

    private String status;
    private Integer code;

}
