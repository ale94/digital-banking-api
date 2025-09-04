package com.ale94.digital_banking_api.infraestructure.mappers;

import org.mapstruct.Mapper;

import com.ale94.digital_banking_api.api.models.responses.TransactionResponse;
import com.ale94.digital_banking_api.domain.entities.TransactionEntity;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    TransactionResponse toTransactionResponse(TransactionEntity entity);
}
