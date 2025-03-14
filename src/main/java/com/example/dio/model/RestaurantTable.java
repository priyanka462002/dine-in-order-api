package com.example.dio.model;

import com.example.dio.enums.TableStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "tables")
public class RestaurantTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "table_id")
    private long tableId;

    @Column(name = "tableNo")
    private long tableNo;

    @Column(name = "table_capacity")
    private long tableCapacity;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private TableStatus tableStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    private Restaurant restaurant;

    @OneToMany(mappedBy = "restaurantTable")
    private List<FoodOrder> foodOrder;

    @OneToOne
    private CartItem cartItem;

}
