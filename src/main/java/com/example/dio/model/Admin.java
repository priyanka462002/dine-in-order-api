package com.example.dio.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Admin extends User{

    @OneToMany(mappedBy = "admin")
    private List<Restaurant> restaurants;

}
