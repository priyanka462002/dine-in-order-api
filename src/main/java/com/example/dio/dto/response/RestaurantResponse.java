package com.example.dio.dto.response;

import com.example.dio.enums.DietType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@Builder
public class RestaurantResponse {

    private long restaurantId;

    private String restaurantName;

    private String address;

    private String contactNumber;

    private String contactEmail;

    private LocalTime opensAt;

    private LocalTime closesAt;

    private List<DietType> dietTypes;

    private List<String>cuisineTypes;
}
