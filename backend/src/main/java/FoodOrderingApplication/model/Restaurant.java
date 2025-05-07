package com.project.Online.Food.Ordering.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private User owner;

    private String name;

    private String description;

    private String cuisineType;

    @OneToOne
    private Address address;

    @Embedded
    private ContactInformation contactInformation;

    private String openingTime;

    @JsonIgnore
    @OneToMany(mappedBy = "restaurant",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();

    @ElementCollection
    @Column(length = 1000)
    private List<String> images;
    private LocalDateTime registrationDate;

    private boolean isOpen;

    @JsonIgnore
    @OneToMany(mappedBy = "restaurant",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Food> foodList = new ArrayList<>();
}
