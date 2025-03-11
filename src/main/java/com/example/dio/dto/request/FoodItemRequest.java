package com.example.dio.dto.request;

import com.example.dio.enums.DietType;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class FoodItemRequest {

    private  String itemName;

    private double price;

    private String description;

    private int stock;

    private DietType dietType;

    private String cuisineType;

    private List<String> categories;

    private LocalDateTime createdAt;

    private LocalDateTime lastModifiedAt;

}
