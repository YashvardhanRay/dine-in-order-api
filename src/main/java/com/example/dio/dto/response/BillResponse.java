package com.example.dio.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class BillResponse {

    private long billId;
    private LocalDateTime generatedAt;
    private double totalPayableAmount;
    private List<OrderResponse> orders;

}
