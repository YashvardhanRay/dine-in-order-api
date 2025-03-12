package com.example.dio.controller;

import com.example.dio.dto.request.FoodItemRequest;
import com.example.dio.dto.response.FoodItemResponse;
import com.example.dio.service.FoodItemService;
import com.example.dio.utility.ListResponseStructure;
import com.example.dio.utility.ResponseBuilder;
import com.example.dio.utility.ResponseStructure;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${app.base-url}")
@AllArgsConstructor
public class FoodItemController {

    private final FoodItemService foodItemService;

    @PostMapping("/fooditems/restaurants/{id}")
    public ResponseEntity<ResponseStructure<FoodItemResponse>> createFoodItem(@PathVariable long id, @RequestBody FoodItemRequest foodItemRequest){
        FoodItemResponse foodItemResponse = foodItemService.createFoodItem(id,foodItemRequest);
        return ResponseBuilder.created("FoodItem Added successfully",foodItemResponse);
    }

    @GetMapping("/fooditems/categories")
    public ResponseEntity<ListResponseStructure<FoodItemResponse>> findByCategories(@RequestParam List<String> categories){
        return  ResponseBuilder.ok("Food item List found according categories",foodItemService.findByTwoCategories(categories));
    }
}
