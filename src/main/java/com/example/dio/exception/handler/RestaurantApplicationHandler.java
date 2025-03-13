package com.example.dio.exception.handler;

import com.example.dio.exception.RestaurantNotFoundException;
import com.example.dio.utility.ResponseBuilder;
import com.example.dio.utility.SimpleErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestaurantApplicationHandler {

    @ExceptionHandler(RestaurantNotFoundException.class)
    public ResponseEntity<SimpleErrorResponse> RestaurantNotFoundException(RestaurantNotFoundException e){
        return ResponseBuilder.notFound(e.getMessage());
    }
}
