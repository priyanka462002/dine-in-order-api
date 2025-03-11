package com.example.dio.model;

import com.example.dio.enums.DietType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "food_items",indexes = {@Index(name = "idx_name",columnList = "itemName")})
@EntityListeners(AuditingEntityListener.class)
public class FoodItem {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "item_id")
    private long itemId;

    @Column(name = "itemName")
    private  String itemName;

    @Column(name = "price")
    private double price;

    @Column(name = "description")
    private String description;

    @Column(name = "stock")
    private int stock;

    @Column(name = "availability")
    private String availability;

    @Column(name = "dietType")
    @Enumerated(EnumType.STRING)
    private DietType dietType;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "last_modified_at")
    private LocalDateTime lastModifiedAt;

    @ManyToOne
    private CuisineType cuisineType;

    @ManyToOne(fetch = FetchType.LAZY)
    private Restaurant restaurant;

    @ManyToMany
    private List<Category> categories;


}
