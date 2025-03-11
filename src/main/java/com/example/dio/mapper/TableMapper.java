package com.example.dio.mapper;

import ch.qos.logback.core.model.ComponentModel;
import com.example.dio.dto.request.TableRequest;
import com.example.dio.dto.response.TableResponse;
import com.example.dio.model.RestaurantTable;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TableMapper {

    RestaurantTable mapToTableEntity(TableRequest tableRequest);

    TableResponse mapToTableResponse(RestaurantTable restaurantTable);
}
