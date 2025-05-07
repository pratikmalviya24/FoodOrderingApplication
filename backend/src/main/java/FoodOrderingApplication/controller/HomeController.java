package com.project.Online.Food.Ordering.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/home")
    public ResponseEntity<String> getWelcomeMessage() {
        System.out.println("Hello World!");
        return new ResponseEntity<>("Order your favourite food", HttpStatus.OK);
    }
}
