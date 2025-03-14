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
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    private  CartItemRepository cartItemRepository;
    private  CartItemMapper cartItemMapper;
    private  FoodItemRepository foodItemRepository;
    private TableRepository tableRepository;

   public CartItemResponse createCartItem(Long itemId, long tableId,int quantity){
       //fetch the FoodItem from the DB
       FoodItem foodItem=foodItemRepository.findById(itemId)
               .orElseThrow(() -> new RuntimeException("Food item not found with id:"+itemId));

       RestaurantTable restaurantTable=tableRepository.findById(tableId)
               .orElseThrow(() -> new RuntimeException("Table not found with id:"+tableId));

       CartItem cartItem=new CartItem();
       cartItem.setFoodItem(foodItem);
       cartItem.setRestaurantTable(restaurantTable);
       cartItem.setQuantity(quantity);
       cartItem.setTotalPrice(calculatorTotalPrice(foodItem,quantity));
       cartItemRepository.save(cartItem);

       return cartItemMapper.mapToCartItem(cartItem);
   }


   public CartItemResponse updateCartItemQuantity(Long cartId,int newQuantity){
       CartItem cartItem=cartItemRepository.findById(cartId)
                       .orElseThrow(() -> new RuntimeException("cart item not found with id:"+cartId));
      cartItemRepository.updateQuantityByCartItemId(cartId,newQuantity);
      cartItem.setCartId(newQuantity);
      cartItem.setTotalPrice(calculatorTotalPrice(cartItem.getFoodItem(),newQuantity));
      return cartItemMapper.mapToCartItem(cartItem);
   }

   public double calculatorTotalPrice(FoodItem foodItem,int quantity){
       return foodItem.getPrice()*quantity;
   }

}
