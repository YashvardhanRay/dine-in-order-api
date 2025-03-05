package com.example.dio.utility;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ResponseBuilder {

    /**
     * Help creating the success response with data
     * including the HttpStatus code, message
     * and data itself
     *
     * @param   status -> creating Ok HttpStatus a
     * @param  message ->
     * @param  <T>
     * @return
     */

    public static <T> ResponseEntity<ResponseStructure<T>> success (HttpStatus status, String message, T data){
        ResponseStructure<T> structure = ResponseStructure.<T>builder()
                .status(status.value())
                .message(message)
                .data(data)
                .build();

        return ResponseEntity.status(status)
                .body(structure);
    }

    /**
     * Help creating the success response with data
     * including the HttpStatus code, message
     * and data itself
     *
     * @param  status -> creating Ok HttpStatus a
     * @param headers
     * @param message ->
     * @param  <T>
     *
     */
    public static <T> ResponseEntity<ResponseStructure<T>> success (HttpStatus status, HttpHeaders headers, String message, T data){
        ResponseStructure<T> structure = ResponseStructure.<T>builder()
                .status(status.value())
                .message(message)
                .data(data)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED)
                .headers(headers)
                .body(structure);
    }

    /**
     * Help creating the ok method response with data
     * including message and T data itself
     *
     * @Params message -> provide message for crated
     * @Params <T> ->
     * @Params return-> we invoke the success method and provide default HttpStatus.Ok
     */
    public static <T> ResponseEntity<ResponseStructure<T>> ok (String message, T data){
        return success(HttpStatus.OK,message,data);
    }

    /**
     * Help creating the create method response with data
     * including message and T data itself
     *
     * @Params message -> provide message for crated
     * @Params <T> -> data invoke in the operation
     * @Params return-> we invoke the success method and provide default HttpStatus.CREATED
     */
    public static <T> ResponseEntity<ResponseStructure<T>> created (String message, T data){
        return success(HttpStatus.CREATED,message,data);
    }

    /**
     * Help creating the error method response with data
     * including HttpStatus and  message
     *
     * @Params HttpStatus -> provide HttpStatus for error
     * @Params message -> Takes proper message for error response
     * @Params return-> ResponseEntity of type SimpleErrorResponse
     */
    public static ResponseEntity<SimpleErrorResponse> error(HttpStatus status, String message) {
        SimpleErrorResponse error = SimpleErrorResponse.builder()
                .type(status.name())
                .message(message)
                .status(status.value())
                .build();

        return ResponseEntity.status(status)
                .body(error);
    }

    /**
     * Help creating the error method response with data
     * including HttpStatus and  message, List<FieldErrorResponse.FieldError>
     *
     * @Params HttpStatus -> provide HttpStatus for error
     * @Params message -> Takes proper message for error response
     * @Params fieldError -> take list of errors
     * @Params return-> ResponseEntity of type SimpleErrorResponse
     * */
    public static ResponseEntity<SimpleErrorResponse> error(HttpStatus status, String message, List<FieldErrorResponse.FieldError > fieldError) {
        FieldErrorResponse error = FieldErrorResponse.builder()
                .type(status.name())
                .message(message)
                .status(status.value())
                .fieldErrors(fieldError)
                .build();

        return ResponseEntity.status(status)
                .body(error);
    }


    /**
     * Help creating the notFound method response with data
     * including message
     * @Params message -> Takes proper message for error response
     * @Params return->
     * */
    public static ResponseEntity<SimpleErrorResponse> notFound(String message) {
       return error(HttpStatus.NOT_FOUND,message);
    }



}
