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

    default CuisineType mapToCuisineType(String cuisineType){
        CuisineType cuisineType1=new CuisineType();
        cuisineType1.setCuisineName(cuisineType);
        return cuisineType1;
    }

    default List<CuisineType> mapCuisines(List<String>CuisineName){
        if(CuisineName==null || CuisineName.isEmpty()){
            return List.of();
        }
        return CuisineName.stream()
                .map(this::mapToCuisineType)
                .collect(Collectors.toList());
    }

    default Category mapToCategory(String category){
        Category category1=new Category();
        category1.setCategory(category);
        return category1;
    }
    default List<Category>map(List<String>categories){
        if (categories==null || categories.isEmpty()){
            return List.of();
        }
        return categories.stream()
                .map(this::mapToCategory)
                .collect(Collectors.toList());
    }

}