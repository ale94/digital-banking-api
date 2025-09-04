package com.ale94.digital_banking_api.infraestructure.mappers;

import org.mapstruct.Mapper;

import com.ale94.digital_banking_api.api.models.responses.AccountResponse;
import com.ale94.digital_banking_api.domain.entities.AccountEntity;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountResponse toAccountResponse(AccountEntity entity);
}
