package com.example.dio.service.impl;

import com.example.dio.dto.request.TableRequest;
import com.example.dio.dto.response.TableResponse;
import com.example.dio.exception.RestaurantNotFoundException;
import com.example.dio.mapper.TableMapper;
import com.example.dio.model.Restaurant;
import com.example.dio.model.RestaurantTable;
import com.example.dio.repository.RestaurantRepository;
import com.example.dio.repository.TableRepository;
import com.example.dio.service.TableService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TableServiceImpl implements TableService {

    private final TableRepository tableRepository;
    private final TableMapper tableMapper;
    private final RestaurantRepository restaurantRepository;

    @Override
    public TableResponse createTable(long restaurantId, TableRequest tableRequest) {
        RestaurantTable restaurantTable = tableMapper.mapToRestaurantTable(tableRequest);

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow( () -> new RestaurantNotFoundException("Restaurant not found !!"));

        restaurantTable.setRestaurant(restaurant);
        tableRepository.save(restaurantTable);
        return tableMapper.mapToTableResponse(restaurantTable);
    }
}
