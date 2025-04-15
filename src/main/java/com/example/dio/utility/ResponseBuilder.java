package com.example.dio.utility;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ResponseBuilder {

    public static <T> ResponseEntity<ListResponseStructure<T>> success (HttpStatus status, String message, List<T> data){
        ListResponseStructure<T> structure = ListResponseStructure.<T>builder()
                .httpStatus(status.value())
                .data(data)
                .message(message)
                .build();

        return ResponseEntity.status(status)
                .body(structure);
    }

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

    public static  ResponseEntity<SimpleResponseStructure> success(HttpStatus httpStatus,HttpHeaders httpHeaders,String message) {
        SimpleResponseStructure res =  SimpleResponseStructure.builder()
                .message(message)
                .httpStatus(httpStatus.value())
                .build();

        return ResponseEntity.status(httpStatus)
                .headers(httpHeaders)
                .body(res);
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

    public static <T> ResponseEntity<ListResponseStructure<T>> ok (String message, List<T> data){
        return success(HttpStatus.OK,message,data);
    }

    public static <T> ResponseEntity<ResponseStructure<T>> ok(HttpHeaders httpHeaders,T data,String message){
        return success(HttpStatus.OK,httpHeaders,message,data);
    }

    /**
     * Help creating the create method response with data
     * including message and T data itself
     *
     * @Params message -> provide message for crated
     * @Params <T> -> data invoke in the operation
     * @Params return-> we invoke the success method and provide default HttpStatus. CREATED
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
     * Help creating the notFound method response with data
     * including message
     * @Params message -> Takes proper message for error response
     * @Params return->
     * */
    public static ResponseEntity<SimpleErrorResponse> notFound(String message) {
       return error(HttpStatus.NOT_FOUND,message);
    }

    public static FieldErrorResponse ValidationError(HttpStatus httpStatus , String message , List<FieldErrorResponse.FieldError> errors){
        return FieldErrorResponse.builder()
                .status(httpStatus.value())
                .message(message)
                .type(httpStatus.name())
                .fieldErrors(errors)
                .build();
    }

}
