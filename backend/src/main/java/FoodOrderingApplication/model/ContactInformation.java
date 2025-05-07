package com.project.Online.Food.Ordering.backend.model;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactInformation {

    @Column(nullable = false,unique = true)
    private String email;
    private String mobile;
    private String twitter;
    private String instagram;
}
