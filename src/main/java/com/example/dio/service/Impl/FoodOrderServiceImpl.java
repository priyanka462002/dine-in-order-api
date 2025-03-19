package com.example.dio.service.Impl;

import com.example.dio.dto.response.CartItemResponse;
import com.example.dio.dto.response.FoodOrderResponse;
import com.example.dio.enums.OrderStatus;
import com.example.dio.mapper.CartItemMapper;
import com.example.dio.mapper.FoodOrderMapper;
import com.example.dio.model.CartItem;
import com.example.dio.model.FoodOrder;
import com.example.dio.model.RestaurantTable;
import com.example.dio.repository.CartItemRepository;
import com.example.dio.repository.FoodOrderRepository;
import com.example.dio.repository.TableRepository;
import com.example.dio.service.FoodOrderService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class FoodOrderServiceImpl implements FoodOrderService {

    private final TableRepository tableRepository;
    private final FoodOrderRepository foodOrderRepository;
    private final CartItemRepository cartItemRepository;
    private final FoodOrderMapper foodOrderMapper;
    private final CartItemMapper cartItemMapper;

    @Override
    @Transactional
    public FoodOrderResponse placeOrder(Long tableId) {
        RestaurantTable table = tableRepository.findById(tableId)
                .orElseThrow(() -> new RuntimeException("table not found"));

        List<CartItem> cartItems=cartItemRepository.findByIsOrderedAndRestaurantTable_TableId(false,tableId);
        FoodOrder foodOrder=new FoodOrder();
        foodOrder.setRestaurantTable(table);
        System.out.println("table"+table);
        foodOrder.setOrderedAt(LocalDateTime.now());
        foodOrder.setCartItems(cartItems);

        foodOrder.setOrderStatus(OrderStatus.UN_BUILD);
        double totalAmount=calculateTotalAmount(cartItems);
        foodOrder.setTotalAmount(totalAmount);


        List<Long> cartItemIds =cartItems.stream()
                .map(CartItem::getCartId)
                .toList();

        List<CartItemResponse> cartItemResponses=cartItems.stream()
                .map(cartItemMapper::mapToCartItem)
                .toList();

        cartItemRepository.updateCartItemsIsOrdered(cartItemIds);
        foodOrderRepository.save(foodOrder);
        return foodOrderMapper.mapToFoodOrder(foodOrder);

    }

    public double calculateTotalAmount(List<CartItem> cartItems){
        double totalAmount=0;
        for(CartItem c:cartItems){
            totalAmount=totalAmount+c.getTotalPrice();
        }
        return totalAmount;
    }


}
