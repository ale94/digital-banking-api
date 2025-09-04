package com.ale94.digital_banking_api.infraestructure.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ale94.digital_banking_api.api.models.responses.UserResponse;
import com.ale94.digital_banking_api.domain.entities.UserEntity;

@Mapper(componentModel = "spring", uses = { AccountMapper.class })
public interface UserMapper {

    @Mapping(source = "account", target = "accountResponse")
    UserResponse toUserResponse(UserEntity entity);

}
