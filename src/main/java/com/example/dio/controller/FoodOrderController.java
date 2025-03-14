package com.example.dio.controller;

import com.example.dio.dto.response.FoodOrderResponse;
import com.example.dio.service.FoodOrderService;
import com.example.dio.util.ResponseBuilder;
import com.example.dio.util.ResponseStructure;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("${app.base-url}")
public class FoodOrderController {

    private FoodOrderService foodOrderService;

    @PostMapping("/orders/tables/{tableId}")
    public ResponseEntity<ResponseStructure<FoodOrderResponse>> placeOrder(@PathVariable Long tableId) {
        FoodOrderResponse orderResponse = foodOrderService.placeOrder(tableId);
        return ResponseBuilder.created(orderResponse,"your order is placed");
    }


}
