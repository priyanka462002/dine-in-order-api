package com.example.dio.dto.request;

import com.example.dio.enums.DietType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
public class RestaurantRequest {
    @NotNull
    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9_]+$",message = "Username can only contain Alphabets,Number and Underscore")
    private String restaurantName;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9_]+$",message = "address can only contains Alphabets,Number and Underscore ")
    private String address;

    @NotNull(message = "Phone number cannot be null")
    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(regexp = "^[7-9]\\d{9}$",message = "Invalid phone number")
    private String contactNumber;

    @NotNull(message = "email cannot be null")
    @NotBlank(message = "email cannot be blank")
    @Email(regexp = "^[A-Za-z0-9._%+-]+@gmail\\.com$",message = "Enter valid email id")
    private String contactEmail;

    @Size(min=1,message = "There must be at least one diet type")
    private List<DietType> dietTypes;

    @Column(name = "opens_at")
    private LocalTime opensAt;

    @Column(name = "closes_at")
    private LocalTime closesAt;



    @Column(name = "created_at")
    private LocalDateTime createdAt;


    @Column(name = "last_modified_at")
    private LocalDateTime lastModifiedAt;


    @NotNull(message = "Cuisine types cannot be null")
    @NotEmpty(message = "At least one cuisine type must be provided")
    @Size(min = 1, message = "There must be at least one cuisine type")
    private List<String>cuisineTypes;

}
