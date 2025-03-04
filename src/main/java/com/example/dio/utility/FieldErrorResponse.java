package com.example.dio.utility;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
public class FieldErrorResponse extends SimpleErrorResponse{

    private List<FieldError> fieldErrors;


    public static FieldError createAFieldError(String message, Object rejectedvalue,String field){
        FieldError error = new FieldError();
        error.message = message;
        error.rejectedvalue= rejectedvalue;
        error.field = field;

        return error;
    }

    @Getter
    public static class FieldError {
        private String message;
        private Object rejectedvalue;
        private String field;
    }

}
