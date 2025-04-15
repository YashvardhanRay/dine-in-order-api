package com.example.dio.security.util;

import com.example.dio.config.AppEnv;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class CookieManager {

    private final AppEnv appEnv;

    public String generateCookie(String name, String value, long maxAge){

        return ResponseCookie.from(name,value)
                .domain(appEnv.getDomain().getName())
                .path("/")
                .sameSite(appEnv.getDomain().getSameSite())
                .httpOnly(true)
                .secure(appEnv.getDomain().isSecure())
                .maxAge(maxAge)
                .build().toString();
    }
}
