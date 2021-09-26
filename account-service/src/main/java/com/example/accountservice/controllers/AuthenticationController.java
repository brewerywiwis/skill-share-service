package com.example.accountservice.controllers;

import com.example.accountservice.entities.User;
import com.example.accountservice.services.jwt.JwtService;
import com.example.accountservice.services.user.UserService;
import com.example.accountservice.types.requests.SignInRequest;
import com.example.accountservice.types.responses.SignInResponse;
import com.example.accountservice.utils.BcryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AuthenticationController {

    private final UserService userService;
    private final JwtService jwtService;

    @Autowired
    public AuthenticationController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/api/v1/signup")
    public ResponseEntity<User> signUp(@RequestBody User user) {
        User result = userService.saveUser(user);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PostMapping("/api/v1/signin")
    public ResponseEntity<SignInResponse> signIn(@RequestBody SignInRequest user, HttpServletRequest request) throws IllegalArgumentException {
        User result = userService.getUser(user.getUsername());
        if (!BcryptUtil.validate(user.getPassword(), result.getPassword())) {
            throw new IllegalArgumentException();
        }
        SignInResponse r = new SignInResponse(jwtService.generateToken(result.getUsername(), result.getRole(), request));
        return new ResponseEntity<>(r, HttpStatus.CREATED);
    }

}