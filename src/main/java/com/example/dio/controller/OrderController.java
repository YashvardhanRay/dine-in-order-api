package com.example.dio.controller;

import com.example.dio.dto.response.OrderResponse;
import com.example.dio.service.OrderService;
import com.example.dio.utility.ResponseBuilder;
import com.example.dio.utility.ResponseStructure;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${app.base-url}")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/orders/tables/{tableId}")
    public ResponseEntity<ResponseStructure<OrderResponse>> createOrder(@PathVariable long tableId){
        OrderResponse orderResponse = orderService.createOrder(tableId);
        return ResponseBuilder.created("Order Created!! ",orderResponse);
    }
}
