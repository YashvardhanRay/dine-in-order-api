package com.example.dio.security.filter;

import com.example.dio.security.jwt.TokenType;
import jakarta.servlet.http.Cookie;

public class FilterHelper {
    public static String extractToken(TokenType tokenType, Cookie[] cookies){
        String token = null;
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals(tokenType.type())) {
                    token = cookie.getValue();
                    break;
                }
            }}
        else {
            return null;
        }
        return token;
    }
}
