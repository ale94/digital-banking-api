package com.ale94.digital_banking_api.api.models.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserResponse implements Serializable {
    private Long id;
    private String name;
    private String email;
    private String phone;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime regDate;
    private String identityDoc;
    private String username;
    private Boolean lock;
    private AccountResponse accountResponse;
}
