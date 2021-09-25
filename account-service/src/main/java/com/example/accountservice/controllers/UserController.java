package com.example.accountservice.controllers;

import com.example.accountservice.entities.User;
import com.example.accountservice.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/v1/users")
    public List<User> getAllUser() {
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return userService.getAllUser();
    }
}