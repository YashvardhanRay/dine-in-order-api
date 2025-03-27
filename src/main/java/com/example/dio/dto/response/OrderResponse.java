package com.example.dio.dto.response;

import com.example.dio.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderResponse {

    private long orderId;
    private OrderStatus orderStatus;
    private LocalDateTime orderAt;
    private double totalAmount;
    private List<CartItemResponse> cartItems;
}
