package com.project.Online.Food.Ordering.backend.services;

import com.project.Online.Food.Ordering.backend.model.User;

public interface UserService {
    public User findUserByJwtToken(String jwt) throws Exception;
    public User findUserByEmail(String email) throws Exception;
}
