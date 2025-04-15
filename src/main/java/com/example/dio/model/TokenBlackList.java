package com.example.dio.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tokens_blacklist")
public class TokenBlackList {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "black_list_id")
    private String blackListId;

    @Column(name = "token")
    private String token;

    @Column(name = "expiration")
    private long expiration;

}
