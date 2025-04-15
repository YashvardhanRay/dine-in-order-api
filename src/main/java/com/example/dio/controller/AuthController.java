package com.example.dio.controller;

import com.example.dio.dto.request.AuthRecord;
import com.example.dio.dto.request.LoginRequest;
import com.example.dio.service.AuthService;
import com.example.dio.service.TokenGenerationService;
import com.example.dio.utility.ResponseBuilder;
import com.example.dio.utility.ResponseStructure;
import com.example.dio.utility.SimpleResponseStructure;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${app.base-url}")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final TokenGenerationService tokenGenerationService;

    @PostMapping("/login")
    public ResponseEntity<ResponseStructure<AuthRecord>> login(@RequestBody LoginRequest loginRequest){
        AuthRecord authRecord = authService.login(loginRequest);
        HttpHeaders httpHeaders = tokenGenerationService.grantAccessAndRefreshToken(authRecord);
        return ResponseBuilder.success(HttpStatus.OK,httpHeaders,"Login successfully !!",authRecord);
    }

    @PostMapping("/refresh-login")
    public ResponseEntity<ResponseStructure<AuthRecord>> refreshLogin(@CookieValue("rt") String refreshToken){
        AuthRecord authRecord = authService.refreshLogin(refreshToken);
        HttpHeaders httpHeaders = tokenGenerationService.grantAccessToken(authRecord);
        return ResponseBuilder.success(HttpStatus.OK,httpHeaders,"New Access Token Generated !!",authRecord);
    }

    @PostMapping("/logout")
    public ResponseEntity<SimpleResponseStructure> logout(@CookieValue("rt") String refreshToken, @CookieValue("at") String accessToken){
        HttpHeaders httpHeaders = authService.logout(refreshToken,accessToken);
        return ResponseBuilder.success(HttpStatus.OK, httpHeaders,"logout !!");
    }
}
