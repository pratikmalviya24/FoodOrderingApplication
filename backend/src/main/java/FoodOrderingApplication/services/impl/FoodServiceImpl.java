package com.project.Online.Food.Ordering.backend.services.impl;

import com.project.Online.Food.Ordering.backend.model.Category;
import com.project.Online.Food.Ordering.backend.model.Food;
import com.project.Online.Food.Ordering.backend.model.Restaurant;
import com.project.Online.Food.Ordering.backend.request.CreateFoodRequest;
import com.project.Online.Food.Ordering.backend.repository.FoodRepository;
import com.project.Online.Food.Ordering.backend.services.FoodService;
import com.project.Online.Food.Ordering.backend.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private RestaurantService restaurantService;

    @Override
    public Food createFood(CreateFoodRequest createFoodRequest, Category category, Restaurant restaurant) throws Exception {
        Food food = Food.builder()
                .foodCategory(createFoodRequest.getCategory())
          a      .description(createFoodRequest.getDescription())
                .images(createFoodRequest.getImages())
                .name(createFoodRequest.getName())
                .price(createFoodRequest.getPrice())
                .ingredientsItems(createFoodRequest.getIngredientsItems())
                .isSeasonable(createFoodRequest.isSeasonal())
                .isVegetarian(createFoodRequest.isVegetarin())
                .creationDate(new Date())
                .restaurant(restaurant).build();

        Food savedFood = foodRepository.save(food);
        restaurant.getFoodList().add(savedFood);
        return savedFood;
    }

    @Override
    public void deleteFood(Long foodId) throws Exception {
        Food food = findFoodById(foodId);
        food.setRestaurant(null);
        foodRepository.save(food);
    }

    @Override
    public List<Food> getRestaurantFood(Long restaurantId, boolean isNonVeg,boolean isSeasonal, String foodCategory) {
        List<Food> foods = foodRepository.findByRestaurantId(restaurantId);

        if(!isNonVeg){
            foods = filterByVegetarian(foods);
        }
        else{
            foods = filterByNonVegetarian(foods);
        }
        if(isSeasonal){
            foods = filterBySeasonal(foods);
        }
        if(foodCategory!=null && !foodCategory.equals("")){
            foods = filterByCategory(foods,foodCategory);
        }

        return foods;
    }

    private List<Food> filterByCategory(List<Food> foods, String foodCategory) {
        return foods.stream()
                .filter(food -> {
                    if(food.getFoodCategory()!=null){
                        return food.getFoodCategory().getName().equals(foodCategory);
                    }
                    return  false;
                })
                .collect(Collectors.toList() );
    }

    private List<Food> filterBySeasonal(List<Food> foods) {
        return foods.stream()
                .filter(food -> food.isSeasonable())
                .collect(Collectors.toList());
    }

    private List<Food> filterByNonVegetarian(List<Food> foods) {
        return foods.stream()
                    .filter(food -> !food.isVegetarian())
                    .collect(Collectors.toList());
    }

    private List<Food> filterByVegetarian(List<Food> foods) {
        return foods.stream()
                    .filter(food -> food.isVegetarian())
                    .collect(Collectors.toList());
    }

    @Override
    public List<Food> searchFood(String keyWord) {
        return foodRepository.searchFood(keyWord);
    }

    @Override
    public Food findFoodById(Long foodId) throws Exception {
        Optional<Food> food  = foodRepository.findById(foodId);
        if(food.isEmpty()){
            throw new Exception("Food Not Exist!");
        }
        return food.get();
    }

    @Override
    public Food updateAvailabilityStatus(Long foodId) throws Exception {
        Food food = findFoodById(foodId);
        food.setAvailable(!food.isAvailable());
        return foodRepository.save(food);
    }
}
