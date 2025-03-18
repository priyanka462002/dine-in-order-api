package com.example.dio.repository;

import com.example.dio.model.CartItem;
import com.example.dio.model.RestaurantTable;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {



    @Modifying
    @Transactional
    @Query("UPDATE CartItem c SET c.quantity = :newQuantity WHERE c.id = :cartItemId")
    int updateQuantityByCartItemId(Long cartItemId, int newQuantity);

    List<CartItem> findByIsOrderedAndRestaurantTable_TableId(Boolean False,Long tableId) ;

    @Modifying
    @Transactional
    @Query("UPDATE CartItem c SET c.isOrdered=true WHERE c.id IN :cartItemIds")
    void updateCartItemsIsOrdered(@Param("cartItemIds")List<Long>cartItemIds);

}
