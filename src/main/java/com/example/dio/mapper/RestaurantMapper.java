package com.example.dio.mapper;


import com.example.dio.dto.request.RestaurantRequest;
import com.example.dio.dto.response.RestaurantResponse;
import com.example.dio.model.CuisineType;
import com.example.dio.model.Restaurant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {

    Restaurant mapToRestaurant(RestaurantRequest restaurantRequest);

    RestaurantResponse mapToRestaurantResponse(Restaurant restaurant);

    default String mapToStringCuisineType(CuisineType cuisineType){
        if(cuisineType == null){
            return  null;
        }
        return cuisineType.getCuisineName();
    }

    default CuisineType mapToCuisineType(String cuisineType){
        if(cuisineType == null){
            return null;
        }
        CuisineType cuisine =new CuisineType();
        cuisine.setCuisineName(cuisineType);
        return cuisine;
    }

}
