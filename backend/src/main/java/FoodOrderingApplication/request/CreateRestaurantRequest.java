package FoodOrderingApplication.request;


import FoodOrderingApplication.model.Address;
import FoodOrderingApplication.model.ContactInformation;
import lombok.Data;

import java.util.List;

@Data
public class CreateRestaurantRequest {
    private Long id;
    private String name;
    private String description;
    private String cuisineType;
    private Address address;
    private ContactInformation contactInformation;
    private String openingTime;
    private List<String> images;
}
