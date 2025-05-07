package com.project.Online.Food.Ordering.backend.controller;

import com.project.Online.Food.Ordering.backend.model.Food;
import com.project.Online.Food.Ordering.backend.model.Restaurant;
import com.project.Online.Food.Ordering.backend.model.User;
import com.project.Online.Food.Ordering.backend.request.CreateFoodRequest;
import com.project.Online.Food.Ordering.backend.services.FoodService;
import com.project.Online.Food.Ordering.backend.services.RestaurantService;
import com.project.Online.Food.Ordering.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
public class FoodController {
    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;


    @GetMapping("/search")
    public ResponseEntity<List<Food>> searchFood(@RequestParam String name,
                                           @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        List<Food> foods= foodService.searchFood(name);
        return new ResponseEntity<>(foods, HttpStatus.CREATED);
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<List<Food>> getRestaurantFood(
            @RequestParam boolean vagetrian,
            @RequestParam boolean seasonal,
            @RequestParam boolean nonVeg,
            @RequestParam(required = false)String foodCategory,
            @PathVariable Long id,
            @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        List<Food> foods= foodService.getRestaurantFood(id,!nonVeg,seasonal,foodCategory);

        return new ResponseEntity<>(foods, HttpStatus.OK);
    }
}
