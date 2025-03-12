package com.example.dio.service.impl;

import com.example.dio.dto.request.FoodItemRequest;
import com.example.dio.dto.response.FoodItemResponse;
import com.example.dio.exception.FoodNotFoundException;
import com.example.dio.exception.RestaurantNotFoundException;
import com.example.dio.mapper.FoodItemMapper;
import com.example.dio.model.Category;
import com.example.dio.model.CuisineType;
import com.example.dio.model.FoodItem;
import com.example.dio.model.Restaurant;
import com.example.dio.repository.CategoryRepository;
import com.example.dio.repository.CuisineRepository;
import com.example.dio.repository.FoodItemRepository;
import com.example.dio.repository.RestaurantRepository;
import com.example.dio.service.FoodItemService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FoodItemServiceImpl implements FoodItemService {
    private final RestaurantRepository restaurantRepository;
    private final FoodItemRepository foodItemRepository;
    private final FoodItemMapper foodItemMapper;
    private final CuisineRepository cuisineRepository;
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public FoodItemResponse createFoodItem(long id, FoodItemRequest foodItemRequest) {

        FoodItem foodItem = foodItemMapper.mapToFoodItem(foodItemRequest);

        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found !!"));

        cuisineRepository.findById(foodItem.getCuisineType().getCuisineName())
                .orElseGet(() -> {
                    CuisineType cuisineType = cuisineRepository.save(foodItem.getCuisineType());
                    restaurant.getCuisineTypes().add(cuisineType);
                    restaurantRepository.save(restaurant);
                    return cuisineType;
                });

        foodItem.setCategories(createNonExistingCuisineTypes(foodItem.getCategories()));
        foodItem.setRestaurant(restaurant);
        foodItem.setCuisineType(foodItem.getCuisineType());

        foodItemRepository.save(foodItem);

        return foodItemMapper.mapToFoodItemResponse(foodItem);
    }

    @Override
    public List<FoodItemResponse> findByTwoCategories(List<String> categories) {
        if(categories.isEmpty()){
            throw new FoodNotFoundException("No food with this categories");
        }
        else{
            List<FoodItemResponse> foodItemList = foodItemMapper.mapToListOfFoodItemResponse(
                    foodItemRepository.findByTwoCategories(
                            categories.stream().distinct().toList(),categories.size()));
            if(foodItemList.isEmpty()){
                throw new FoodNotFoundException("No food with this categories");
            }
            else {
                return foodItemList;
            }
        }
    }


    private List<Category> createNonExistingCuisineTypes(List<Category> categories) {
        return categories.stream()
                .map(type -> categoryRepository.findById(type.getCategory())
                        .orElseGet(() -> categoryRepository.save(type)))
                .toList();
    }
}
