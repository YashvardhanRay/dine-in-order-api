package com.example.dio.utility;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SimpleErrorResponse {
    private String type;
    private int status; // 404
    private String message; // failed to update the user, The user is not found by the given id
}
