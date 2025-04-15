package com.example.dio.security.jwt;

import com.example.dio.model.TokenBlackList;
import com.example.dio.repository.TokenBlackListRepository;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TokenBlackListService {

    private final TokenBlackListRepository tokenBlackListRepository;
    private final JWTService jwtService;

    public boolean isBlackListed(String token){
        return tokenBlackListRepository.existsByToken(token);
    }

    public void blackListToken(String token){

        Claims claims =jwtService.parseToken(token);
        long expiration = claims.getExpiration().getTime();

        TokenBlackList tokenBlackList = new TokenBlackList();
        tokenBlackList.setToken(token);
        tokenBlackList.setExpiration(expiration);

        tokenBlackListRepository.save(tokenBlackList);
    }
}
