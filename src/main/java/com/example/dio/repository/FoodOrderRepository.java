package com.example.dio.repository;

import com.example.dio.enums.OrderStatus;
import com.example.dio.model.FoodOrder;
import com.example.dio.model.Restaurant;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodOrderRepository extends JpaRepository<FoodOrder,Long> {

    List<FoodOrder> findByOrderStatusAndRestaurantTable_TableId(OrderStatus status, long tableId);

    @Modifying
    @Transactional
    @Query("UPDATE FoodOrder t SET t.orderStatus = :status WHERE t.orderId IN :orderIds")
    void updateFoodOrderOrderStatus(@Param("orderIds") List<Long> orderIds,
                                     @Param("status") OrderStatus status);


}
