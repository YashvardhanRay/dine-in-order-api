package com.example.dio.mapper;

import com.example.dio.dto.request.RestaurantRequest;
import com.example.dio.dto.response.RestaurantResponse;
import com.example.dio.model.CuisineType;
import com.example.dio.model.Restaurant;
import org.mapstruct.Mapper;


/**
 * The {@code RestaurantMapper} interface is responsible for mapping
 * between {@link RestaurantRequest}, {@link RestaurantResponse}, and {@link Restaurant} entities.
 * <p>
 * This interface uses MapStruct for automatic mapping and is annotated
 * with {@code @Mapper} to indicate it as a Spring component.
 */
@Mapper(componentModel = "spring")
public interface RestaurantMapper {

    /**
     * Converts a {@link RestaurantRequest} DTO to a {@link RestaurantResponse} entity.
     *
     * @param restaurant the request DTO containing restaurant details
     * @return the mapped {@link RestaurantResponse} entity
     */
    RestaurantResponse mapToRestaurantResponse(Restaurant restaurant);

    /**
     * Converts a {@link RestaurantRequest} DTO to a {@link Restaurant} entity.
     *
     * @param restaurantRequest the request DTO containing restaurant details
     * @return the mapped {@link Restaurant} entity
     */
    Restaurant mapToRestaurantEntity(RestaurantRequest restaurantRequest);

    /**
     * getting string value and storing in CuisineType object
     *
     * @param value cuisine type getting in string type
     * @return object of cuisine with string name
     */
    default CuisineType map(String value){
        if (value != null){
            CuisineType cuisineType = new CuisineType();
            cuisineType.setCuisineName(value);
            return cuisineType;
        }
        else
            return null;
    }

    /**
     * converting cuisine type into String for restaurant response
     *
     * @param cuisineType CuisineType object with name
     * @return cuisine name as string
     */
    default String map(CuisineType cuisineType){
        if ( cuisineType != null){
            return cuisineType.getCuisineName();
        }else
            return null;
    }

}
