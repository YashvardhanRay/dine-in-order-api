package com.example.dio.dto.request;

import com.example.dio.enums.DietType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodItemRequest {
    private String name;
    private double price;
    private String Description;
    private int stock;
    private DietType dietType;
    private String cuisineType;
}
