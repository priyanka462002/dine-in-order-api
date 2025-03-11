package com.example.dio.service.Impl;

import com.example.dio.dto.request.TableRequest;
import com.example.dio.dto.response.TableResponse;
import com.example.dio.exception.RestaurantNotFoundByIdException;
import com.example.dio.mapper.TableMapper;
import com.example.dio.model.Restaurant;
import com.example.dio.model.RestaurantTable;
import com.example.dio.repository.RestaurantRepository;
import com.example.dio.repository.TableRepository;
import com.example.dio.service.RestaurantTableService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class TableServiceImpl implements RestaurantTableService {

    private TableRepository tableRepository;
    private RestaurantRepository restaurantRepository;
    private TableMapper tableMapper;

    @Override
    public TableResponse registerTable(TableRequest tableRequest, Long restaurantId) {
        Restaurant foundRestaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantNotFoundByIdException("Restaurant Not Found By Id "));

        RestaurantTable restaurantTable = tableMapper.mapToTableEntity(tableRequest);
        restaurantTable.setRestaurant(foundRestaurant);

        //restaurantTable.setTableNo(generateNextTableNo(restaurantId));

        tableRepository.save(restaurantTable);
        return tableMapper.mapToTableResponse(restaurantTable);

}

      //private  int generateNextTableNo(Long restaurantId){
       // Integer maxTableNo =tableRepository.findMaxTableNoByIdRestaurant(restaurantId);
      //  return (maxTableNo==null)? 1:maxTableNo +1;

//}

}
