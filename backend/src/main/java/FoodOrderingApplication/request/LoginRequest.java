package com.project.Online.Food.Ordering.backend.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
