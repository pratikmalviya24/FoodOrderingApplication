package com.project.Online.Food.Ordering.backend.services;

import com.project.Online.Food.Ordering.backend.dto.RestaurantDto;
import com.project.Online.Food.Ordering.backend.model.Restaurant;
import com.project.Online.Food.Ordering.backend.model.User;
import com.project.Online.Food.Ordering.backend.request.CreateRestaurantRequest;

import java.util.List;

public interface RestaurantService {
    public Restaurant createRestaurant (CreateRestaurantRequest restaurantRequest, User user);
    public Restaurant updateRestaurant(Long restaurantId,CreateRestaurantRequest update) throws Exception;
    public void deleteRestaurant(Long restaurantId) throws Exception;
    public List<Restaurant> getAllRestaurant();
    public List<Restaurant> searchRestaurant(String keyWord);
    public Restaurant findRestaurantById(Long id) throws Exception;
    public Restaurant getRestaurantByUserId(Long userId) throws Exception;
    public RestaurantDto addFavouriteRestaurant(Long id,User user) throws Exception;
    public Restaurant updateRestaurantStatus(Long id) throws Exception;
}
