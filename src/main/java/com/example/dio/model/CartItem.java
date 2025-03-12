package com.example.dio.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "cartItems")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cartId;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "totalPrice")
    private double totalPrice;

    @Column(name = "isOrdered")
    private String isOrdered;

    @ManyToOne
    private RestaurantTable restaurantTable;

    @ManyToOne
    private FoodItem foodItem;

}
