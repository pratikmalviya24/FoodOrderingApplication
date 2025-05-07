package com.project.Online.Food.Ordering.backend.controller;

import com.project.Online.Food.Ordering.backend.model.IngredientsCategory;
import com.project.Online.Food.Ordering.backend.model.IngredientsItem;
import com.project.Online.Food.Ordering.backend.request.IngredientCategoryRequest;
import com.project.Online.Food.Ordering.backend.request.IngredientItemRequest;
import com.project.Online.Food.Ordering.backend.services.IngredientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/ingredients")
public class IngredientController {
    @Autowired
    private IngredientsService ingredientsService;
    @PostMapping("/category")
    public ResponseEntity<IngredientsCategory> createIngredientsCategory(
            @RequestBody IngredientCategoryRequest ingredientCategoryRequest
            ) throws Exception {
        IngredientsCategory ingredientsCategory = ingredientsService.createIngredientsCategory(
          ingredientCategoryRequest.getName(),
                ingredientCategoryRequest.getRestaurantId()
        );
        return new ResponseEntity<>(ingredientsCategory, HttpStatus.CREATED);
    }

    @PostMapping()
    public ResponseEntity<IngredientsItem> createIngredientItem(
            @RequestBody IngredientItemRequest ingredientItemRequest
    ) throws Exception {
        IngredientsItem ingredientsItem = ingredientsService.createIngredientsItem(
                ingredientItemRequest.getRestaurantId(),
                ingredientItemRequest.getName(),
                ingredientItemRequest.getCategoryId()
        );
        return new ResponseEntity<>(ingredientsItem, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/stoke")
    public ResponseEntity<IngredientsItem> updateIngredientStock(
            @PathVariable Long id
    ) throws Exception {
        IngredientsItem ingredientsItem = ingredientsService.updateStock(id);
        return new ResponseEntity<>(ingredientsItem, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<List<IngredientsItem>> getRestaurantIngredient(
            @PathVariable Long id
    ) throws Exception {
        List<IngredientsItem> ingredientsItems = ingredientsService.findRestaurantsByIngredients(id);
        return new ResponseEntity<>(ingredientsItems, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}/category")
    public ResponseEntity<List<IngredientsCategory>> getRestaurantIngredientCategory(
            @PathVariable Long id
    ) throws Exception {
        List<IngredientsCategory>ingredientsCategories = ingredientsService.findIngredientsCategoriesByRestaurantsId(id);
        return new ResponseEntity<>(ingredientsCategories, HttpStatus.OK);
    }


}
