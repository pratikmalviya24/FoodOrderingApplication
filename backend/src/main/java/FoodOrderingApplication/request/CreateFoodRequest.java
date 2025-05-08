package FoodOrderingApplication.request;

import FoodOrderingApplication.model.Category;
import FoodOrderingApplication.model.IngredientsItem;
import FoodOrderingApplication.model.Restaurant;
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

    private Category foodCategory;
    private List<String> images;

    private Long restaurantId;
    private boolean isVegetarian;
    private boolean isSeasonable;

    private List<IngredientsItem> ingredientsItems;
}
