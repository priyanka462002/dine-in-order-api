package com.example.dio.service.Impl;

import com.example.dio.dto.response.CartItemResponse;
import com.example.dio.dto.response.FoodItemResponse;
import com.example.dio.mapper.CartItemMapper;
import com.example.dio.model.CartItem;
import com.example.dio.model.FoodItem;
import com.example.dio.model.RestaurantTable;
import com.example.dio.repository.CartItemRepository;
import com.example.dio.repository.FoodItemRepository;
import com.example.dio.repository.TableRepository;
import com.example.dio.service.CartItemService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartItemServiceImpl implements CartItemService {

    private  CartItemRepository cartItemRepository;
    private  CartItemMapper cartItemMapper;
    private  FoodItemRepository foodItemRepository;
    private TableRepository tableRepository;

   public CartItemResponse createCartItem(Long itemId, long tableId,int quantity){
       //fetch the FoodItem from the DB
       FoodItem foodItem=foodItemRepository.findById(itemId)
               .orElseThrow(() -> new RuntimeException("requested food item Id not available"));

       RestaurantTable restaurantTable=tableRepository.findById(tableId)
               .orElseThrow(() -> new RuntimeException("Requested table Id not available"));
       CartItem cartItem=new CartItem();
       cartItem.setQuantity(quantity);
       cartItem.setRestaurantTable(restaurantTable);
       cartItem.setFoodItem(foodItem);
       double totalPrice=quantity*foodItem.getPrice();
       cartItem.setTotalPrice(totalPrice);
       return cartItemMapper.mapToCartItemResponse(cartItem);
   }


   public CartItemResponse updateQuantity(Long cartId,int quantity){
       CartItem cartItem=cartItemRepository.findById(cartId)
                       .orElseThrow(() -> new RuntimeException("cart item with id not found"));
       cartItem.setQuantity(quantity);
       cartItem.setTotalPrice(cartItem.getFoodItem().getPrice() * quantity);
      return cartItemMapper.mapToCartItemResponse(cartItemRepository.save(cartItem));
   }


}
