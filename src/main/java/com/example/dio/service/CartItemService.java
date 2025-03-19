package com.example.dio.service;

import com.example.dio.dto.response.CartItemResponse;
import com.example.dio.dto.response.FoodItemResponse;
import com.example.dio.model.CartItem;

public interface CartItemService {

   CartItemResponse createCartItem(Long itemId, Long tableId, int quantity);
   CartItemResponse updateCartItemQuantity(Long cartId,int newQuantity);

}

