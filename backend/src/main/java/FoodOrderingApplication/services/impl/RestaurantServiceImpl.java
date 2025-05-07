package com.project.Online.Food.Ordering.backend.services.impl;

import com.project.Online.Food.Ordering.backend.dto.RestaurantDto;
import com.project.Online.Food.Ordering.backend.model.Address;
import com.project.Online.Food.Ordering.backend.model.Restaurant;
import com.project.Online.Food.Ordering.backend.model.User;
import com.project.Online.Food.Ordering.backend.repository.AddressRepository;
import com.project.Online.Food.Ordering.backend.repository.RestaurantRepository;
import com.project.Online.Food.Ordering.backend.repository.UserRepository;
import com.project.Online.Food.Ordering.backend.request.CreateRestaurantRequest;
import com.project.Online.Food.Ordering.backend.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRespository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Restaurant createRestaurant(CreateRestaurantRequest restaurantRequest, User user) {
        Address address  = addressRepository.save(restaurantRequest.getAddress());
        Restaurant restaurant = Restaurant.builder()
                .address(address)
                .owner(user)
                .contactInformation(restaurantRequest.getContactInformation())
                .cuisineType(restaurantRequest.getCuisineType())
                .images(restaurantRequest.getImages())
                .name(restaurantRequest.getName())
                .openingTime(restaurantRequest.getOpeningTime())
                .registrationDate(LocalDateTime.now())
                .description(restaurantRequest.getDescription())
                .build();
        return restaurantRespository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest update) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);

        if (update.getName() != null) {
            restaurant.setName(update.getName());
        }
        if (update.getAddress() != null) {
            restaurant.setAddress(update.getAddress());
        }
        if (update.getDescription() != null) {
            restaurant.setDescription(update.getDescription());
        }
        if (update.getImages() != null) {
            restaurant.setImages(update.getImages());
        }
        if (update.getCuisineType() != null) {
            restaurant.setCuisineType(update.getCuisineType());
        }
        if (update.getOpeningTime() != null) {
            restaurant.setOpeningTime(update.getOpeningTime());
        }


        return restaurantRespository.save(restaurant); // Return the updated restaurant
    }


    @Override
    public void deleteRestaurant(Long restaurantId) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);
        restaurantRespository.delete(restaurant);
    }

    @Override
    public List<Restaurant> getAllRestaurant() {
        return restaurantRespository.findAll();
    }

    @Override
    public List<Restaurant> searchRestaurant(String keyWord) {
        return restaurantRespository.findBySearchQuery(keyWord);
    }

    @Override
    public Restaurant findRestaurantById(Long id) throws Exception {
        Optional<Restaurant> opt = restaurantRespository.findById(id);
        if(!opt.isPresent()){
            throw  new Exception("Restaurant Not found with id : "+id);
        }
        return opt.get();
    }

    @Override
    public Restaurant getRestaurantByUserId(Long userId) throws Exception {
        Restaurant restaurant = restaurantRespository.findByOwnerId(userId);
        if(restaurant==null){
            throw  new Exception("Restaurant Not found with id : "+userId);
        }
        return restaurant;
    }

    @Override
    public RestaurantDto addFavouriteRestaurant(Long id, User user) throws Exception {
        Restaurant restaurant = findRestaurantById(id);
        RestaurantDto restaurantDto = RestaurantDto.builder()
                .description(restaurant.getDescription())
                .images(restaurant.getImages())
                .title(restaurant.getName())
                .id(restaurant.getId())
                .build();
//        if(user.getFavourites().contains(restaurantDto)){
//            user.getFavourites().remove(restaurantDto);
//        }
//        else{
//            user.getFavourites().add(restaurantDto);
//        }

        boolean isFavourite = false;
        List<RestaurantDto> favourites = user.getFavourites();

        for(RestaurantDto restaurantDto1 : favourites){
            if(restaurantDto1.getId().equals(restaurant.getId())){
                isFavourite = true;
                break;
            }
        }

        if(isFavourite){
            favourites.removeIf(favourite->favourite.getId().equals(restaurant.getId()));
        }
        else{
            favourites.add(restaurantDto);
        }

        userRepository.save(user);
        return restaurantDto;
    }

    @Override
    public Restaurant updateRestaurantStatus(Long id) throws Exception {
        Restaurant restaurant = findRestaurantById(id);
        restaurant.setOpen(!restaurant.isOpen());
        return restaurantRespository.save(restaurant);
    }
}

