package com.example.dio.dto.request;

import com.example.dio.enums.UserRole;

public record AuthRecord(
        long userId,
        String name,
        String email,
        UserRole userRole,
        long accessExpiration,
        long refreshExpiration
) {}
