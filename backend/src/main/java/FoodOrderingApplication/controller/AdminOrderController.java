package com.project.Online.Food.Ordering.backend.controller;

import com.project.Online.Food.Ordering.backend.model.Order;
import com.project.Online.Food.Ordering.backend.model.User;
import com.project.Online.Food.Ordering.backend.request.OrderRequest;
import com.project.Online.Food.Ordering.backend.services.OrderService;
import com.project.Online.Food.Ordering.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminOrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;


    @GetMapping("/order/restaurant/{id}")
    public ResponseEntity<List<Order>> getOrderHistory(
            @PathVariable Long id,
            @RequestParam(required = false) String orderStatus,
            @RequestHeader("Authorizaton") String jwt) throws Exception
    {
        User user = userService.findUserByJwtToken(jwt);
        List<Order> orders= orderService.getRestaurantOrder(id,orderStatus);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PutMapping("/order/{orderId}/{orderStatus}")
    public ResponseEntity<Order> updateOrderStatus(
            @PathVariable Long id,
            @PathVariable String orderStatus,
            @RequestHeader("Authorizaton") String jwt) throws Exception
    {
        User user = userService.findUserByJwtToken(jwt);
        Order order= orderService.updateOrder(id,orderStatus);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
