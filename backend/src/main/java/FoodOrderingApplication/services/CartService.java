package com.project.Online.Food.Ordering.backend.services;

import com.project.Online.Food.Ordering.backend.model.Cart;
import com.project.Online.Food.Ordering.backend.model.CartItem;
import com.project.Online.Food.Ordering.backend.request.AddCartItemRequest;

public interface CartService {
    public CartItem addItemToCart(AddCartItemRequest addCartItemRequest, String jwt) throws Exception;

    public CartItem updateCartItemQuantity(Long cartItemId,int quantity) throws Exception;

    public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception;

    public Long calculateCartTotals(Cart cart) throws Exception;

    public Cart findCartById(Long id) throws Exception;

    public Cart findCartByUserId(Long id) throws Exception;

    public Cart clearCart(Long  id) throws Exception;
}
