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

    private FoodItemResponse foodItemResponse;

    private int quantity;

    private double totalPrice;

}
