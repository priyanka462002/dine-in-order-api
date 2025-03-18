package com.example.dio.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BillResponse {

    private Long billId;

    private LocalDateTime generatedAt;

    private double totalPayableAmount;

    private List<FoodOrderResponse> orders;


}
