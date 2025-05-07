package com.project.Online.Food.Ordering.backend.response;

import com.project.Online.Food.Ordering.backend.model.Role;
import lombok.Data;

@Data
public class AuthResponse {
    private String jwt;
    private String message;
    private Role role;
}
