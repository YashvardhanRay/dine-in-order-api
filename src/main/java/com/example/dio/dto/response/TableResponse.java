package com.example.dio.dto.response;

import com.example.dio.enums.TableStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TableResponse {

    private long id;
    private String tableNumber;
    private int tableCapacity;
    private TableStatus status;
}

