package com.example.dio.controller;

import com.example.dio.dto.request.TableRequest;
import com.example.dio.dto.response.TableResponse;
import com.example.dio.model.Restaurant;
import com.example.dio.service.RestaurantTableService;
import com.example.dio.util.ResponseBuilder;
import com.example.dio.util.ResponseStructure;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("${app.base-url}")
@RestController
@AllArgsConstructor
@Tag(name="Table Controller", description = "Endpoints for managing restaurant data")
public class TableController {

    private RestaurantTableService tableService;

    @PostMapping("tables/restaurants/{restaurantId}")
    public ResponseEntity<ResponseStructure<TableResponse>> registerTable(@RequestBody @Valid TableRequest tableRequest, @PathVariable Long restaurantId) {

        TableResponse tableResponse = tableService.registerTable(tableRequest,restaurantId);

        return ResponseBuilder.success(HttpStatus.CREATED, "Table Created", tableResponse);
}
}
