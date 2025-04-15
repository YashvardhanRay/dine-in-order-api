package com.example.dio.security.jwt;

import java.time.Instant;
import java.util.Map;

public record TokenPayload(
        Map<String,Object> claims,
        Instant issueAt,
        Instant expiration
) {}
