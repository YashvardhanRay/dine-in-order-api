package com.example.dio.security.jwt;

import com.example.dio.config.AppEnv;
import com.example.dio.exception.InvaildJWTException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTService{

    private final AppEnv appEnv;

    public JWTService(AppEnv appEnv) {
        this.appEnv = appEnv;
    }

    public Claims parseToken(String token) {
        try{
            return Jwts.parserBuilder()
                    .setSigningKey(KeyHolder.getKey(appEnv.getSecurity().getSecret()))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        }
        catch(JwtException | IllegalArgumentException _){
            throw new InvaildJWTException("Failed to pass token,Invalid JWT");
        }
    }

    public String generateToken(TokenPayload tokenPayload){
        return Jwts.builder()
                .setClaims(tokenPayload.claims())
                .setIssuedAt( Date.from(tokenPayload.issueAt()))
                .setExpiration( Date.from(tokenPayload.expiration()))
                .signWith(KeyHolder.getKey(appEnv.getSecurity().getSecret()), SignatureAlgorithm.HS256)
                .compact();
    }
}
