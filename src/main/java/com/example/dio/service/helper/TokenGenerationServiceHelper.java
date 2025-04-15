package com.example.dio.service.helper;

import com.example.dio.config.AppEnv;
import com.example.dio.security.jwt.JWTService;
import com.example.dio.security.jwt.TokenPayload;
import com.example.dio.security.jwt.TokenType;
import com.example.dio.security.util.CookieManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;

@Component
@AllArgsConstructor
public class TokenGenerationServiceHelper{

    private final JWTService jwtService;
    private final AppEnv appEnv;
    private final CookieManager cookieManager;

    public String generateToken(TokenType tokenType, Map<String,Object> claims, Instant shouldExpireAt){

        TokenPayload payload = generateTokenPayload(tokenType,claims,shouldExpireAt);

        String token = jwtService.generateToken(payload);

        long maxAge = Duration.between(Instant.now(),shouldExpireAt).getSeconds();

        return cookieManager.generateCookie(tokenType.type(),token,maxAge);
    }

    public TokenPayload generateTokenPayload(TokenType tokenType, Map<String, Object> claims, Instant shouldExpireAt){

        Instant issueAt = calculateIssueTime(tokenType, shouldExpireAt);

        return new TokenPayload(claims,issueAt,shouldExpireAt);

    }

    private Instant calculateIssueTime(TokenType tokenType, Instant shouldExpireAt) {
        Instant issueAt;

        switch (tokenType){
            case ACCESS -> {
                issueAt = shouldExpireAt.minusSeconds(appEnv.getSecurity().getTokenValidity().getAccessValidity());
            }
            case REFRESH -> {
                issueAt = shouldExpireAt.minusSeconds(appEnv.getSecurity().getTokenValidity().getRefreshValidity());
            }
            default -> throw new IllegalArgumentException("Illegal Token Type Specified !!");
        }
        return issueAt;
    }
}
