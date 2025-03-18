package com.example.dio.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItemResponse {

    private long cartId;

    private FoodItemResponse foodItem;

    private int quantity;

    private double totalPrice;

}
