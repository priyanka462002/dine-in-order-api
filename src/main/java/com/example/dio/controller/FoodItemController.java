package com.example.dio.controller;

import com.example.dio.dto.request.FoodItemRequest;
import com.example.dio.dto.response.FoodItemResponse;
import com.example.dio.model.Restaurant;
import com.example.dio.service.FoodItemService;
import com.example.dio.util.FieldErrorResponse;
import com.example.dio.util.ResponseStructure;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("${app.base-url}")
public class FoodItemController {
    private FoodItemService foodItemService;

    @PostMapping("/foodItems/restaurants/{restaurantId}")
    @Operation(description = "Create a new FoodItem ",
            responses = {
                    @ApiResponse(responseCode = "201", description = "FoodItem Created"),
                    @ApiResponse(responseCode = "400", description = "Invalid Input", content = {
                            @Content(schema = @Schema(implementation = FieldErrorResponse.class))
                    }),
                    @ApiResponse(responseCode = "403", description = "FoodItem is not present", content = {
                            @Content(schema = @Schema(implementation = FieldErrorResponse.class))
                    })
            })
    public ResponseEntity<ResponseStructure<FoodItemResponse>> createFoodItem(@PathVariable Long restaurantId, @RequestBody @Valid FoodItemRequest foodItemRequest) {
        FoodItemResponse foodItemResponse = foodItemService.createFoodItem(restaurantId, foodItemRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseStructure.create(HttpStatus.CREATED, "Food item created", foodItemResponse));
    }


    public ResponseEntity<ResponseStructure<List<FoodItemResponse>>> getFoodItemsByCategories(@RequestParam("category") List<String> categories) {
       List<FoodItemResponse>foodItemResponses=foodItemService.getFoodItemsByCategories(categories);

       return ResponseEntity.status(HttpStatus.FOUND)
               .body(ResponseStructure.create(HttpStatus.FOUND,"Food items found",foodItemResponses));
    }

    @GetMapping("/foodItems/restaurant/{restaurantId}")
    public ResponseEntity<ResponseStructure<List<FoodItemResponse>>> getAllFoodItems(@RequestParam("restaurantId")Long restaurantId){
        List<FoodItemResponse> responses=foodItemService.getFoodItemsByRestaurant(restaurantId);
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(ResponseStructure.create(HttpStatus.FOUND,"Food items found",responses));
}


}
