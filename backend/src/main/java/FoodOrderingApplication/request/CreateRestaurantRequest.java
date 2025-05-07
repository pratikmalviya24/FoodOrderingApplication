package com.project.Online.Food.Ordering.backend.request;


import com.project.Online.Food.Ordering.backend.model.Address;
import com.project.Online.Food.Ordering.backend.model.ContactInformation;
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
