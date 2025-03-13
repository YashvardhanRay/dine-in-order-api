package com.example.dio.exception;

public class FoodNotFoundException extends RuntimeException {

    private String message;

    public FoodNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage (){
        return this.message;
    }
}
