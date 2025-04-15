package com.example.dio.service;

import com.example.dio.dto.request.FoodItemRequest;
import com.example.dio.dto.response.FoodItemResponse;

import java.util.List;

public interface FoodItemService {

    FoodItemResponse createFoodItem(long id , FoodItemRequest foodItemRequest);
    List<FoodItemResponse> findByTwoCategories(List<String> categories);
    List<FoodItemResponse> findByRestaurantId(long restaurantId);
}
