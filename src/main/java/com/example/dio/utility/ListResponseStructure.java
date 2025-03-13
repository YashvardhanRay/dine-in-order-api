package com.example.dio.utility;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ListResponseStructure<T> {
    private int httpStatus;
    private String message;
    private List<T> data;
}
