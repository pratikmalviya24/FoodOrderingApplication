package com.project.Online.Food.Ordering.backend.request;

import com.project.Online.Food.Ordering.backend.model.Category;
import com.project.Online.Food.Ordering.backend.model.IngredientsItem;
import com.project.Online.Food.Ordering.backend.model.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateFoodRequest {
    private String name;
    private String description;
    private Long price;

    private Category category;
    private List<String> images;

    private Long restaurantId;
    private boolean vegetarin;
    private boolean seasonal;

    private  List<IngredientsItem> ingredientsItems;
}
