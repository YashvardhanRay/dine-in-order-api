package com.example.dio.security.jwt;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class KeyHolder {

    private static Key key;

    public static Key getKey(String secret){
        if(key == null) {
            key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        }
        return key;
    }
}
