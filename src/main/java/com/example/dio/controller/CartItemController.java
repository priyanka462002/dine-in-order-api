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
@RequestMapping("${app.base-url}")
public class CartItemController {

    private CartItemService cartItemService;

    @PostMapping("/tables/{tableId}/cartItems/foodItems/{itemId}")
    public ResponseEntity<ResponseStructure<CartItemResponse>>createCartItem(@PathVariable long itemId,@PathVariable long tableId,@RequestParam("quantity") int quantity){
        CartItemResponse cartItemResponse=cartItemService.createCartItem(itemId,tableId,quantity);
        return  ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseStructure.create(HttpStatus.CREATED,"cart item created",cartItemResponse));
    }

    @PatchMapping("/cartItems/{cartId}")
    public ResponseEntity<ResponseStructure<CartItemResponse>> updateCartItemQuantity(@PathVariable Long cartId, @RequestParam("quantity") int quantity){
        //add food items to the cart
        CartItemResponse cartItemResponse = cartItemService.updateCartItemQuantity(cartId,quantity);
        return  ResponseBuilder.success(HttpStatus.CREATED,"Food item added to the cart successfully",cartItemResponse);
}


}
