package com.example.accountservice.services.user;

import com.example.accountservice.entities.User;
import com.example.accountservice.types.requests.EditUserRequest;
import com.example.accountservice.types.requests.SearchUserRequest;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<User> getAllUser();

    User saveUser(User user);

    User getUser(String username);

    User getUserByCriteria(SearchUserRequest criteria);

    List<User> getUserInformationById(List<UUID> uuids);

    User editUser(String username, EditUserRequest editUserRequest);
}