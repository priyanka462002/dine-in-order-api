package com.example.dio.repository;

import com.example.dio.model.RestaurantTable;
import jakarta.persistence.criteria.From;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TableRepository extends JpaRepository<RestaurantTable,Long> {

    @Query("SELECT MAX(t.tableNo) FROM RestaurantTable t WHERE t.restaurant.restaurantId = :restaurantId")
    Integer findMaxTableNoByIdRestaurant(@Param("restaurantId") Long restaurantId);


}
