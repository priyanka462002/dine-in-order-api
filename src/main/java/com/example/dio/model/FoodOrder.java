package com.example.dio.model;

import com.example.dio.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class FoodOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderId")
    private  long orderId;

    @Column(name = "orderStatus")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(name = "ordered_At")
    private LocalDateTime orderedAt;

    @Column(name = "totalAmount")
    private  double totalAmount;

    @ManyToOne
    private RestaurantTable restaurantTable;

    @OneToMany
    private List<CartItem> cartItems;


}
