package com.project.Online.Food.Ordering.backend.services.impl;

import com.project.Online.Food.Ordering.backend.model.Cart;
import com.project.Online.Food.Ordering.backend.model.CartItem;
import com.project.Online.Food.Ordering.backend.model.Food;
import com.project.Online.Food.Ordering.backend.model.User;
import com.project.Online.Food.Ordering.backend.repository.CartItemRepository;
import com.project.Online.Food.Ordering.backend.repository.CartRepository;
import com.project.Online.Food.Ordering.backend.request.AddCartItemRequest;
import com.project.Online.Food.Ordering.backend.services.CartService;
import com.project.Online.Food.Ordering.backend.services.FoodService;
import com.project.Online.Food.Ordering.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private FoodService foodService;

    @Override
    public CartItem addItemToCart(AddCartItemRequest addCartItemRequest, String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Food food = foodService.findFoodById(addCartItemRequest.getFoodId());
        Cart cart = cartRepository.findByCustomerId(user.getId());

        for(CartItem cartItem : cart.getCartItemList()){
            if(cartItem.getFood().equals(food)){
                int newQuantity = cartItem.getQuantity() +addCartItemRequest.getQuantity();
                return updateCartItemQuantity(cartItem.getId(),newQuantity);
            }
        }

        CartItem newCartItem = CartItem.builder()
                .food(food)
                .cart(cart)
                .quantity(addCartItemRequest.getQuantity())
                .ingredients(addCartItemRequest.getIngredients())
                .totalPrice(addCartItemRequest.getQuantity()*food.getPrice()).build();

        CartItem savedCartItem = cartItemRepository.save(newCartItem);
        cart.getCartItemList().add(newCartItem);

        return savedCartItem;
    }

    @Override
    public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception {
        Optional<CartItem> opt = cartItemRepository.findById(cartItemId);
        if(opt.isEmpty()){
            throw  new Exception("Cart Item Not Found");
        }
        CartItem cartItem = opt.get();
        cartItem.setQuantity(quantity);

        cartItem.setTotalPrice(cartItem.getFood().getPrice()*quantity);
        return cartItemRepository.save(cartItem);
    }

    @Override
    public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Cart cart = cartRepository.findByCustomerId(user.getId());
        Optional<CartItem> opt = cartItemRepository.findById(cartItemId);
        if(opt.isEmpty()){
            throw  new Exception("Cart Item Not Found");
        }
        CartItem cartItem = opt.get();
        cart.getCartItemList().remove(cartItem);
        return cartRepository.save(cart);
    }

    @Override
    public Long calculateCartTotals(Cart cart) throws Exception {
        Long total = 0L;

        for(CartItem cartItem : cart.getCartItemList()){
            total+=cartItem.getFood().getPrice()*cartItem.getQuantity();
        }

        return total;
    }

    @Override
    public Cart findCartById(Long id) throws Exception {
        Optional<Cart> opt = cartRepository.findById(
                id
        );

        if(opt.isEmpty()){
            throw new Exception("Cart Not Found!");
        }

        return opt.get();
    }

    @Override
    public Cart findCartByUserId(Long userId) throws Exception {
//        User user = userService.findUserByJwtToken(jwt);
        Cart  cart =  cartRepository.findByCustomerId(userId);
        cart.setTotal(calculateCartTotals(cart));
        return cart;
    }

    @Override
    public Cart clearCart(Long userId ) throws Exception {
        Cart cart = findCartByUserId(userId);
        cart.getCartItemList().clear();
        return cartRepository.save(cart);
    }
}
