package com.example.accountservice.services.user;

import com.example.accountservice.entities.User;

import java.util.List;

public interface UserService {
    List<User> getAllUser();

    User saveUser(User user);

    User getUser(String username);
}