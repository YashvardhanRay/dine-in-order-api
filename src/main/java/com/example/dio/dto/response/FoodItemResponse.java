package com.example.dio.dto.response;

import com.example.dio.enums.DietType;
import com.example.dio.enums.StockStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class FoodItemResponse {
    private long id;
    private String name;
    private double price;
    private String Description;
    private StockStatus availability;
    private DietType dietType;
    private String cuisineType;
    private LocalDate createdat;
    private LocalDateTime lastmodifiedat;
    private List<String> categories;
}
