package com.example.dio.utility;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseBuilder {

    public static <T> ResponseEntity<ResponseStructure<T>> success (HttpStatus status, String message, T data){
        ResponseStructure<T> structure = ResponseStructure.<T>builder()
                .status(status.value())
                .message(message)
                .data(data)
                .build();

        return ResponseEntity.status(status)
                .body(structure);
    }
    public static <T> ResponseEntity<ResponseStructure<T>> success (HttpHeaders headers, HttpStatus status, String message, T data){
        ResponseStructure<T> structure = ResponseStructure.<T>builder()
                .status(status.value())
                .message(message)
                .data(data)
                .build();

        return ResponseEntity.status(status)
                .headers(headers)
                .body(structure);
    }
}
