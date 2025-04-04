package com.example.dio.mapper;

import com.example.dio.dto.response.BillResponse;
import com.example.dio.model.Bill;
import com.example.dio.model.Category;
import com.example.dio.model.CuisineType;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface BillMapper {
    public BillResponse mapToBillResponse(Bill bill);
    List<String> mapToListString(List<Category> value);
//    List<String> map(List<Image> value);
//    default String map(Image value){
//        if(value != null){
//            return value.getImageURL();
//        }
//        else return null;
//    }
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
