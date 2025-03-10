package com.example.dio.mapper;

import com.example.dio.dto.request.FoodItemRequest;
import com.example.dio.dto.response.FoodItemResponse;
import com.example.dio.model.Category;
import com.example.dio.model.CuisineType;
import com.example.dio.model.FoodItem;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import javax.smartcardio.CardTerminal;
import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface FoodItemMapper {
    FoodItem mapToFoodItem(FoodItemRequest foodItemRequest);
    FoodItemResponse mapToFoodItemResponse (FoodItem foodItem);

    default String mapToString(CuisineType value) {
        if(value == null) {
            return null;
        }
        else return value.getCuisineName().toLowerCase();
    }

    default CuisineType mapToCuisineType(String value) {
        if(value == null) {
            return null;
        }
        else {
            CuisineType type = new CuisineType();
            type.setCuisineName(value);
            return type;
        }
    }

    default Category mapToCategory (String category){
        if(category == null){
            return null;
        }
        else {
            Category category1 = new Category();
            category1.setCategory(category);
            return category1;
        }
    }

    default String mapToString(Category value) {
        if(value == null) {
            return null;
        }
        else return value.getCategory().toLowerCase();
    }
}
