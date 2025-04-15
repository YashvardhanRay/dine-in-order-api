package com.example.dio.repository;

import com.example.dio.model.TokenBlackList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TokenBlackListRepository extends JpaRepository<TokenBlackList,String> {

    boolean existsByToken(String token);
    List<TokenBlackList> findByExpirationLessThan(long current);

}
