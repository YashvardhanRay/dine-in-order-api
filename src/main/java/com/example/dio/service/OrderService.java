package com.example.dio.service;

import com.example.dio.dto.response.OrderResponse;

public interface OrderService {
    OrderResponse createOrder(long tableId);
}
