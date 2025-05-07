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
@RequestMapping("/api")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;

    @PostMapping("/order")
    public ResponseEntity<Order> createOrder(
            @RequestBody OrderRequest orderRequest,
            @RequestHeader("Authorizaton") String jwt) throws Exception
    {
        User user = userService.findUserByJwtToken(jwt);
        Order order = orderService.createOrder(orderRequest,user);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/order/user")
    public ResponseEntity<List<Order> > getOrderHistory(
            @RequestHeader("Authorizaton") String jwt) throws Exception
    {
        User user = userService.findUserByJwtToken(jwt);
        List<Order> orders= orderService.getUsersOrder(user.getId());
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
