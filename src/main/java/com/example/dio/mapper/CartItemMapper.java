package com.example.dio.mapper;

import com.example.dio.dto.response.CartItemResponse;
import com.example.dio.model.CartItem;
import com.example.dio.model.Category;
import com.example.dio.model.CuisineType;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface CartItemMapper {
    public CartItemResponse mapToCartItemResponse(CartItem cartItem);
    /*List<String> map(List<Image> value);
    default String map(Image value){
        if(value != null){
            return value.getImageUR();
        }
        else return null;
    }*/

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
