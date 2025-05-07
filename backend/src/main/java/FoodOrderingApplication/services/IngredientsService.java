package com.project.Online.Food.Ordering.backend.services;

import com.project.Online.Food.Ordering.backend.model.IngredientsCategory;
import com.project.Online.Food.Ordering.backend.model.IngredientsItem;

import java.util.List;

public interface IngredientsService {

    public IngredientsCategory createIngredientsCategory(String name,Long id) throws Exception;
    public IngredientsCategory findIngredientsCategoryById(Long id) throws Exception;
    public List<IngredientsCategory> findIngredientsCategoriesByRestaurantsId(Long id) throws Exception;
    public IngredientsItem createIngredientsItem(Long restaurantId,String ingredient,Long categoryId) throws Exception;
    public List<IngredientsItem> findRestaurantsByIngredients(Long id);
    public IngredientsItem updateStock(Long id) throws Exception;

}
