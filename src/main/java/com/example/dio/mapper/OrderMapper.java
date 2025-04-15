package com.example.dio.mapper;

import com.example.dio.dto.response.OrderResponse;
import com.example.dio.model.Category;
import com.example.dio.model.CuisineType;
import com.example.dio.model.Order;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface OrderMapper {
    public OrderResponse mapToOrderResponse(Order order);
    List<String> mapToListString(List<Category> value);

    default String mapToString(Category value) {
        if(value == null) {
            return null;
        }
        else return value.getCategory().toLowerCase();
    }
    default String mapToString(CuisineType value) {
        if(value == null) {
            return null;
        }
        else return value.getCuisineName().toLowerCase();
    }
}
