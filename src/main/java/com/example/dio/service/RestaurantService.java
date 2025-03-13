package com.example.dio.service;

import com.example.dio.dto.request.RestaurantRequest;
import com.example.dio.dto.response.RestaurantResponse;
import com.example.dio.model.Restaurant;
import com.example.dio.model.User;
import jakarta.validation.Valid;

public interface RestaurantService {

    RestaurantResponse createRestaurant(@Valid RestaurantRequest restaurantRequest, long userId);

}
