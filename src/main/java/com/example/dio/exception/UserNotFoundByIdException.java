package com.example.dio.exception;

public class UserNotFoundByIdException extends RuntimeException {

    private String message;

    public UserNotFoundByIdException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
