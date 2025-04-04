package com.example.dio.service;

import com.example.dio.dto.response.CartItemResponse;

public interface CartItemService {

     CartItemResponse createCartItem(long tableId, long foodId, int quantity);

     CartItemResponse updateQuantity(long cartId, int quantity);
}
