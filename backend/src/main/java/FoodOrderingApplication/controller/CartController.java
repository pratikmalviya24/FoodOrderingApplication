package FoodOrderingApplication.controller;

import FoodOrderingApplication.model.Cart;
import FoodOrderingApplication.model.CartItem;
import FoodOrderingApplication.model.User;
import FoodOrderingApplication.request.AddCartItemRequest;
import FoodOrderingApplication.request.UpdateCartItemRequest;
import FoodOrderingApplication.services.CartService;
import FoodOrderingApplication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;
    @PutMapping("/cart/add")
    public ResponseEntity<CartItem> addItemToCart(
            @RequestBody AddCartItemRequest cartItemRequest,
            @RequestHeader("Authorization") String jwt
            ) throws Exception {

        CartItem cartItem =  cartService.addItemToCart(cartItemRequest,jwt);
         return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }

    @PutMapping("/cartItem/update")
    public ResponseEntity<CartItem> updateCartItemQuantity(
            @RequestBody UpdateCartItemRequest updateCartItemRequest,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {

        CartItem cartItem =  cartService.updateCartItemQuantity(
                updateCartItemRequest.getCartItemId(),
                updateCartItemRequest.getQuantity());

        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }

    @DeleteMapping("/cartItem/{id}/remove")
    public ResponseEntity<Cart> removeCartItem(
            @PathVariable Long id,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        Cart cart =  cartService.removeItemFromCart(id,jwt);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PutMapping("/cart/clear")
    public ResponseEntity<Cart> clearCart(
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
      Cart cartItem = cartService.clearCart(user.getId());
      return new ResponseEntity<>(cartItem,HttpStatus.OK);
    }

    @GetMapping("/cart")
    public ResponseEntity<Cart> findUserCart(
        @RequestHeader("Authorization") String jwt
    ) throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        Cart cart = cartService.findCartByUserId(user.getId());
        return new ResponseEntity<>(cart,HttpStatus.OK);
    }


}
