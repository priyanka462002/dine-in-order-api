package com.example.dio.controller;

import com.example.dio.dto.request.RestaurantRequest;
import com.example.dio.dto.response.RestaurantResponse;
import com.example.dio.service.RestaurantService;
import com.example.dio.util.FieldErrorResponse;
import com.example.dio.util.ResponseBuilder;
import com.example.dio.util.ResponseStructure;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@AllArgsConstructor
@RequestMapping("${app.base-url}")
@Tag(name="RestaurantController", description = "Endpoints for managing restaurant data")
public class RestaurantController {

    private RestaurantService restaurantService;

    @PostMapping("/restaurants/{userId}")
    @Operation(description = "Create a new restaurant if the user is an admin",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Restaurant Created"),
                    @ApiResponse(responseCode = "400", description = "Invalid Input", content = {
                            @Content(schema = @Schema(implementation = FieldErrorResponse.class))
                    }),
                    @ApiResponse(responseCode = "403", description = "User is not an admin", content = {
                            @Content(schema = @Schema(implementation = FieldErrorResponse.class))
                    })
            })
   public ResponseEntity<ResponseStructure<RestaurantResponse>> createRestaurant(@PathVariable long userId,@Valid @RequestBody RestaurantRequest restaurantRequest){
        RestaurantResponse restaurantResponse=restaurantService.createRestaurant(restaurantRequest,userId);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseStructure.create(HttpStatus.CREATED,"Restaurant created successfully",restaurantResponse));
    }
}


