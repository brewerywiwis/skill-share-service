package com.example.accountservice.controllers;

import com.example.accountservice.entities.User;
import com.example.accountservice.services.user.UserService;
import com.example.accountservice.types.SecurityName;
import com.example.accountservice.types.UserPrincipal;
import com.example.accountservice.types.requests.EditUserRequest;
import com.example.accountservice.types.requests.GetUserByIdList;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @SecurityRequirement(name = SecurityName.SKILLSHAREAPI)
    @GetMapping("/api/v1/users")
    public List<User> getAllUser(@AuthenticationPrincipal UserPrincipal principle) {
        return userService.getAllUser();
    }

    //    @SecurityRequirement(name = SecurityName.SKILLSHAREAPI)
//    @GetMapping("/api/v1/user")
//    public List<User> getUserByCriteria(@RequestBody User userCriteria) {
//        return userService.getAllUser();
//    }
    @SecurityRequirement(name = SecurityName.SKILLSHAREAPI)
    @GetMapping("/api/v1/me")
    public User getMe(@AuthenticationPrincipal UserPrincipal user) {
        return userService.getUser(user.getUsername());
    }

    @SecurityRequirement(name = SecurityName.SKILLSHAREAPI)
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/api/v1/user")
    public User editUser(@AuthenticationPrincipal UserPrincipal user, @RequestBody EditUserRequest body) {
        return userService.editUser(user.getUsername(), body);
    }

    @PostMapping("/api/v1/users/list")
    public List<User> getUserByIds(@RequestBody GetUserByIdList req){
        return userService.getUserInformationById(req.getIdList());
    }

    @GetMapping("/api/v1/user")
    @ResponseStatus(HttpStatus.OK)
    public User getUserByUsername(@RequestParam String username){
        return userService.getUser(username);
    }
}