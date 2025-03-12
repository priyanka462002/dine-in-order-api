package com.example.dio.controller;

import com.example.dio.dto.response.CartItemResponse;
import com.example.dio.dto.response.FoodItemResponse;
import com.example.dio.service.CartItemService;
import com.example.dio.util.ResponseBuilder;
import com.example.dio.util.ResponseStructure;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class CartItemController {

    private CartItemService cartItemService;

    @PostMapping("/tables/{tableId}/cartItem/itemId/{itemId}")
    public ResponseEntity<ResponseStructure<CartItemResponse>>createCartItem(@PathVariable long tableId,@PathVariable long itemId,@RequestParam int quantity){
        CartItemResponse cartItemResponse=cartItemService.createCartItem(itemId,tableId,quantity);
        return  ResponseBuilder.ok(cartItemResponse,"cart items created");
    }

    @PatchMapping("/tables/{tableId}/cartItems/foodItems/{itemId}")
    public ResponseEntity<ResponseStructure<CartItemResponse>> addFoodItemsToCart(@PathVariable Long cartId, @PathVariable int quantity){
        //add food items to the cart
        CartItemResponse cartItemResponse = cartItemService.updateQuantity(cartId,quantity);
        return  ResponseBuilder.success(HttpStatus.CREATED,"Food item added to the cart successfully",cartItemResponse);
}


}
