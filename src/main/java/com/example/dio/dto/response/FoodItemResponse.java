package com.example.dio.dto.response;

import com.example.dio.enums.DietType;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class FoodItemResponse {


    private long itemId;

    private  String itemName;

    private double price;

    private String description;

    private String availability;

    private int stock;

    private DietType dietType;
}
