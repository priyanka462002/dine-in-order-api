package com.example.dio.mapper;

import com.example.dio.dto.request.FoodItemRequest;
import com.example.dio.dto.response.FoodItemResponse;
import com.example.dio.model.Category;
import com.example.dio.model.CuisineType;
import com.example.dio.model.FoodItem;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface FoodItemMapper {

    FoodItem mapToFoodItemEntity(FoodItemRequest foodItemRequest);

    FoodItemResponse mapToFoodItemResponse(FoodItem foodItem);


    default String mapToString(CuisineType value) {
        if (value == null) {
            return null;
        }
        return value.getCuisineName();
    }

    default String mapToString(Category value) {
        if (value == null) {
            return null;
        }
        return value.getCategory();
    }

    default CuisineType mapToCuisineType(String value) {
        if (value == null) {
            return null;
        } else {
            CuisineType type = new CuisineType();
            type.setCuisineName(value.toLowerCase());
            return type;
        }
    }

    default Category mapToCategory (String value){
        if (value == null) {
            return null;
        } else {
            Category type = new Category();
            type.setCategory(value.toLowerCase());
            return type;
   }

}
}