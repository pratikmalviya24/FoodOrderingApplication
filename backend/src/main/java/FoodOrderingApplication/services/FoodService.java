package com.project.Online.Food.Ordering.backend.services;

import com.project.Online.Food.Ordering.backend.model.Category;
import com.project.Online.Food.Ordering.backend.model.Food;
import com.project.Online.Food.Ordering.backend.model.Restaurant;
import com.project.Online.Food.Ordering.backend.request.CreateFoodRequest;

import java.util.List;

public interface FoodService {
    public Food createFood(CreateFoodRequest createFoodRequest, Category category, Restaurant restaurant) throws Exception;

    void deleteFood(Long foodId) throws Exception;

    public List<Food> getRestaurantFood(Long restaurantId,boolean isNonVeg,boolean isSeasonal,String foodCategory);

    public List<Food> searchFood(String keyWord);

    public Food findFoodById(Long foodId) throws Exception;

    public Food updateAvailabilityStatus(Long foodId) throws Exception;
 }
