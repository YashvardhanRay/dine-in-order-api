package com.example.dio.service;

import com.example.dio.dto.request.AuthRecord;
import com.example.dio.dto.request.LoginRequest;
import org.springframework.http.HttpHeaders;

public interface AuthService {

    AuthRecord login(LoginRequest loginRequest);

    AuthRecord refreshLogin(String refreshToken);

    HttpHeaders logout(String refreshToken, String accessToken);
}
