package com.example.dio.service.Impl;

import com.example.dio.dto.request.FoodItemRequest;
import com.example.dio.dto.response.FoodItemResponse;
import com.example.dio.mapper.FoodItemMapper;
import com.example.dio.model.Category;
import com.example.dio.model.CuisineType;
import com.example.dio.model.FoodItem;
import com.example.dio.model.Restaurant;
import com.example.dio.repository.CategoryRepository;
import com.example.dio.repository.CuisineTypeRepository;
import com.example.dio.repository.FoodItemRepository;
import com.example.dio.repository.RestaurantRepository;
import com.example.dio.service.FoodItemService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FoodItemServiceImpl implements FoodItemService {

    private final FoodItemRepository foodItemRepository;
    private final RestaurantRepository restaurantRepository;
    private final FoodItemMapper foodItemMapper;
    private final CuisineTypeRepository cuisineTypeRepository;
    private final CategoryRepository categoryRepository;

   @Transactional
    @Override
    public FoodItemResponse createFoodItem(Long restaurantId, FoodItemRequest foodItemRequest) {

       Restaurant restaurant = restaurantRepository.findById(restaurantId)
               .orElseThrow(() -> new RuntimeException("Restaurant not found by id:" + restaurantId));


       CuisineType cuisineType=cuisineTypeRepository.findById(foodItemRequest.getCuisineType())
               .orElseGet(() ->{
                 CuisineType cuisineType1=new CuisineType();
                 cuisineType1.setCuisineName(foodItemRequest.getCuisineType());
                 return cuisineTypeRepository.save(cuisineType1);
               });

       FoodItem foodItem=foodItemMapper.mapToFoodItemEntity(foodItemRequest);
       foodItem.setCategories(this.createNonExistingCategory(foodItem.getCategories()));
       foodItem.setRestaurant(restaurant);
       foodItem.setCuisineType(cuisineType);
       foodItem.setAvailability(updateFoodAvailability(foodItem.getStock(),foodItem.getAvailability()));

       foodItemRepository.save(foodItem);

       return  foodItemMapper.mapToFoodItemResponse(foodItem);
   }

    private String updateFoodAvailability(int stock, String availability) {
       return availability=(stock>0)?"AVAILABLE":"OUT OF STOCK";
    }


    public  List<FoodItemResponse>getFoodItemsByCategories(List<String> categories){
       long categoryCount=(long) categories.size();
       List<FoodItem> foodItems=foodItemRepository.findFoodItemsByCategoryName(categories,categoryCount);
       return foodItems.stream()
               .map(foodItemMapper::mapToFoodItemResponse)
               .collect(Collectors.toList());
   }

   private List<Category>createNonExistingCategory(List<Category>categories){
       return categories.stream()
               .map( type -> categoryRepository.findById(type.getCategory())
               .orElseGet(() -> categoryRepository.save(type)))
       .toList();
   }

   public List<FoodItemResponse> getFoodItemsByRestaurant(Long restaurantId){
       List<FoodItem> foodItems=foodItemRepository.findFoodItemsByRestaurantId(restaurantId);
       return foodItems.stream()
               .map(foodItemMapper::mapToFoodItemResponse)
               .collect(Collectors.toList());
   }

}


