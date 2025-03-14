package com.example.dio.dto.response;

import com.example.dio.enums.OrderStatus;
import com.example.dio.model.CartItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class FoodOrderResponse {

    private long  orderId;

    private OrderStatus orderStatus;

    private LocalDateTime orderedAt;

    private double totalAmount;

    private Long tableId;




}
