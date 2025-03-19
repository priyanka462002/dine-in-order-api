package com.example.dio.repository;

import com.example.dio.model.FoodItem;
import com.example.dio.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FoodItemRepository extends JpaRepository<FoodItem ,Long> {

    @Query("SELECT f FROM FoodItem f JOIN f.categories c " +
            "WHERE c.category IN :categories " +
            "GROUP BY f " +
            "HAVING COUNT(DISTINCT c) = :categoryCount")
    List<FoodItem> findFoodItemsByCategoryName(@Param("categories") List<String> categories, @Param("categoryCount") Long categoryCount);

    @Query("SELECT f FROM FoodItem f WHERE f.restaurant.restaurantId =:restaurantId")
    List<FoodItem> findFoodItemsByRestaurantId(@Param("restaurantId")Long restaurantId);

    @Query("Select restaurant from FoodItem where itemId= :itemId")
    Restaurant findRestaurant_RestaurantNameByItemId(@Param("itemId") long itemId);


}
