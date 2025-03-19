package com.example.dio.service.Impl;

import com.example.dio.dto.request.RestaurantRequest;
import com.example.dio.dto.response.RestaurantResponse;
import com.example.dio.exception.UnauthorizedUserException;
import com.example.dio.exception.UserNotFoundByIdException;
import com.example.dio.mapper.RestaurantMapper;
import com.example.dio.model.Admin;
import com.example.dio.model.CuisineType;
import com.example.dio.model.Restaurant;
import com.example.dio.model.User;
import com.example.dio.repository.CuisineTypeRepository;
import com.example.dio.repository.RestaurantRepository;
import com.example.dio.repository.UserRepository;
import com.example.dio.service.RestaurantService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Transactional
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    private final UserRepository userRepository;

    private final RestaurantMapper restaurantMapper;

    private final CuisineTypeRepository cuisineTypeRepository;


    @Override
    public RestaurantResponse createRestaurant(RestaurantRequest restaurantRequest, long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundByIdException("Failed to find the user,User not found by id"));

        if(!(user instanceof Admin admin)){
            throw new UnauthorizedUserException("only Admin is allowed to create a restaurant");
        }

        //Map DTO to entity
        Restaurant restaurant=restaurantMapper.mapToRestaurant(restaurantRequest);
        restaurant.setAdmin(admin);

        List<CuisineType>existingList=restaurant.getCuisineTypes();
        List<CuisineType>newList=createNonExistingCuisine(existingList);
        restaurant.setCuisineTypes(newList);

        restaurantRepository.save(restaurant);

        //Map to response

        return restaurantMapper.mapToRestaurantResponse(restaurant);
    }

    private List<CuisineType> createNonExistingCuisine(List<CuisineType> cuisineTypes) {
        return cuisineTypes.stream()
                .map(existingCuisine ->
                        cuisineTypeRepository.findById(existingCuisine.getCuisineName())
                                .orElseGet(()-> cuisineTypeRepository.save(existingCuisine)))
                .toList();
  }
}
