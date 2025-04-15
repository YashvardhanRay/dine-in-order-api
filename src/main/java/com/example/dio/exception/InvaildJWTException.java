package com.example.dio.exception;

public class InvaildJWTException extends RuntimeException {
    private String message;
    public InvaildJWTException(String message) {
        this.message = message;
    }
}
