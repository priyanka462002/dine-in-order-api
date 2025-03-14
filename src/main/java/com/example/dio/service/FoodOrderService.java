package com.example.dio.service;

import com.example.dio.dto.response.FoodOrderResponse;
import com.example.dio.model.CartItem;

import java.util.List;

public interface FoodOrderService {
    FoodOrderResponse placeOrder(Long tableId);

}
