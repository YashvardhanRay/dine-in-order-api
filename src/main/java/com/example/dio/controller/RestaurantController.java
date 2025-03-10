package com.example.dio.controller;

import com.example.dio.dto.request.RestaurantRequest;
import com.example.dio.dto.response.RestaurantResponse;
import com.example.dio.service.RestaurantService;
import com.example.dio.utility.ResponseBuilder;
import com.example.dio.utility.ResponseStructure;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("${app.base-url}")
public class RestaurantController {

    private RestaurantService restaurentService;

    @PostMapping("/Restaurant/{userId}")
    public ResponseEntity<ResponseStructure<RestaurantResponse>> createRestaurant(
            @PathVariable long userId, @RequestBody RestaurantRequest restaurantRequest){

        RestaurantResponse restaurastResponse =
                restaurentService.createRestaurant(userId,restaurantRequest);

        return ResponseBuilder.created(
                "Restaurant Added successfully !! ",restaurastResponse);
    }

}
