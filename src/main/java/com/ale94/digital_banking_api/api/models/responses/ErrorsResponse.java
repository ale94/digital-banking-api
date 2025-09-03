package com.ale94.digital_banking_api.api.models.responses;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
public class ErrorsResponse extends BaseErrorResponse {

    private List<String> errors;

}
