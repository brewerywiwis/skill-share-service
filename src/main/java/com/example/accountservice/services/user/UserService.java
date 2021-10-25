package com.example.accountservice.services.user;

import com.example.accountservice.entities.User;
import com.example.accountservice.types.requests.SearchUserRequest;

import java.util.List;

public interface UserService {
    List<User> getAllUser();

    User saveUser(User user);

    User getUser(String username);

    User getUserByCriteria(SearchUserRequest criteria);
}