package com.example.dio.mapper;


import com.example.dio.dto.request.TableRequest;
import com.example.dio.dto.response.TableResponse;
import com.example.dio.model.RestaurantTable;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface TableMapper {

    RestaurantTable mapToRestaurantTable(TableRequest tableRequest);

    TableResponse mapToTableResponse(RestaurantTable restaurantTable);
}
