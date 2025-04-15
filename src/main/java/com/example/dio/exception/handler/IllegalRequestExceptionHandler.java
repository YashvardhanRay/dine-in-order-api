package com.example.dio.exception.handler;

import com.example.dio.exception.IllegalRequestException;
import com.example.dio.utility.ResponseBuilder;
import com.example.dio.utility.SimpleErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class IllegalRequestExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<SimpleErrorResponse> IllegalRequestException(IllegalRequestException e){
        return ResponseBuilder.notFound(e.getMessage());
    }
}
