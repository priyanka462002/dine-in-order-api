package com.example.dio.mapper;

import com.example.dio.dto.response.FoodOrderResponse;
import com.example.dio.model.FoodOrder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FoodOrderMapper {
    FoodOrderResponse mapToFoodOrder(FoodOrder foodOrder);

}
