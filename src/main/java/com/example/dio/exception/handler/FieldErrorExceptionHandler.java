package com.example.dio.exception.handler;

import com.example.dio.utility.FieldErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.List;

public class FieldErrorExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        /*List<FieldErrorResponse.FieldError> errors = new ArrayList<>();

        List<ObjectError> objectErrors = ex.getAllErrors();

        for(ObjectError error : objectErrors){
            FieldError fieldError = (FieldError) error;
            FieldErrorResponse errorResponse = FieldErrorResponse.createAFieldError(
                    error.getDefaultMessage(),
                    fieldError.getRejectedValue(),
                    fieldError.getField());

        }*/

        List<FieldErrorResponse.FieldError> errors = ex.getAllErrors().stream()
                .map(error -> (FieldError) error)
                .map(this::createFieldError)
                .toList();

        FieldErrorResponse fieldErrorResponse = createFieldErrorResponse(status, errors);

        return ResponseEntity.status(status).body(errors);
    }

    private FieldErrorResponse createFieldErrorResponse(HttpStatusCode status, List<FieldErrorResponse.FieldError> errors) {
        return FieldErrorResponse.builder()
                .type(status.toString())
                .status(status.value())
                .message("Invalid Input")
                .fieldErrors(errors)
                .build();
    }

    private FieldErrorResponse.FieldError createFieldError(FieldError fieldError) {
        FieldErrorResponse.FieldError error = FieldErrorResponse.createAFieldError(
                fieldError.getDefaultMessage(),
                fieldError.getRejectedValue(),
                fieldError.getField());
        return error;
    }

}
