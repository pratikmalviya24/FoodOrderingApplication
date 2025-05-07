package com.project.Online.Food.Ordering.backend.services.impl;

import com.project.Online.Food.Ordering.backend.model.IngredientsCategory;
import com.project.Online.Food.Ordering.backend.model.IngredientsItem;
import com.project.Online.Food.Ordering.backend.model.Restaurant;
import com.project.Online.Food.Ordering.backend.repository.IngredientsCategoryRepository;
import com.project.Online.Food.Ordering.backend.repository.IngredientsItemRepository;
import com.project.Online.Food.Ordering.backend.services.IngredientsService;
import com.project.Online.Food.Ordering.backend.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientsServiceImpl implements IngredientsService {

    @Autowired
    private IngredientsItemRepository ingredientsItemRepository;
    @Autowired
    private IngredientsCategoryRepository ingredientsCategoryRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Override
    public IngredientsCategory createIngredientsCategory(String name, Long id) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(id);
        IngredientsCategory category =IngredientsCategory.builder()
                .restaurant(restaurant)
                .name(name)
                .build();

        return ingredientsCategoryRepository.save(category);
    }

    @Override
    public IngredientsCategory findIngredientsCategoryById(Long id) throws Exception {
        Optional<IngredientsCategory> opt = ingredientsCategoryRepository.findById(id);
        if(opt.isEmpty()){
            throw new Exception("Ingredients Category not found!");
        }
        return opt.get();
    }

    @Override
    public List<IngredientsCategory> findIngredientsCategoriesByRestaurantsId(Long id) throws Exception {
        restaurantService.findRestaurantById(id);
        return ingredientsCategoryRepository.findByRestaurantId(id);
    }

    @Override
    public IngredientsItem createIngredientsItem(Long restaurantId, String ingredient, Long categoryId) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        IngredientsCategory category = findIngredientsCategoryById(categoryId);
        IngredientsItem item = IngredientsItem.builder()
                .name(ingredient)
                .restaurant(restaurant)
                .ingredientsCategory(category).build();
        IngredientsItem ingredientsItem = ingredientsItemRepository.save(item);
        category.getIngredientsItemList().add(ingredientsItem);
        return ingredientsItem;
    }

    @Override
    public List<IngredientsItem> findRestaurantsByIngredients(Long id) {
        return ingredientsItemRepository.findByRestaurantId(id);
    }

    @Override
    public IngredientsItem updateStock(Long id) throws Exception {
        Optional<IngredientsItem> opt = ingredientsItemRepository.findById(id);
        if(opt.isEmpty()){
            throw new Exception("Ingredient Not Found!");
        }
        IngredientsItem ingredientsItem = opt.get();
        ingredientsItem.setInStock(!ingredientsItem.isInStock());
        return ingredientsItemRepository.save(ingredientsItem);
    }
}
