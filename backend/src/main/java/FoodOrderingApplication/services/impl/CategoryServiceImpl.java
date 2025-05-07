package com.project.Online.Food.Ordering.backend.services.impl;

import com.project.Online.Food.Ordering.backend.model.Category;
import com.project.Online.Food.Ordering.backend.model.Restaurant;
import com.project.Online.Food.Ordering.backend.repository.CategoryRepository;
import com.project.Online.Food.Ordering.backend.services.CategoryService;
import com.project.Online.Food.Ordering.backend.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public Category createCategory(String name, Long userId) throws Exception {
        Restaurant restaurant = restaurantService.getRestaurantByUserId(userId);
        Category category = Category.builder()
                .name(name)
                .restaurant(restaurant)
                .build();
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findCategoryByRestaurantId(Long id) throws Exception {
        Restaurant restaurant = restaurantService.getRestaurantByUserId(id);
        return categoryRepository.findByRestaurantId(id);
    }

    @Override
    public Category findCategoryById(Long id) throws Exception {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(optionalCategory.isEmpty()){
            throw new Exception("Category Not Found!");
        }
        return optionalCategory.get();
    }
}
