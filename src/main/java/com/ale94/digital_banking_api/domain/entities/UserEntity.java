package com.ale94.digital_banking_api.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity(name = "user")
public class UserEntity implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;
    private String email;
    private String phone;
    private LocalDateTime creationDate;
    private String identityDocument;
    private String username;
    private String password;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToOne(
            mappedBy = "user"
    )
    private AccountEntity account;

}
