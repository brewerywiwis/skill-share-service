package com.example.accountservice.controllers;

import com.example.accountservice.entities.User;
import com.example.accountservice.services.jwt.JwtService;
import com.example.accountservice.services.user.UserService;
import com.example.accountservice.types.requests.SignInRequest;
import com.example.accountservice.types.responses.AuthenticateResponse;
import com.example.accountservice.types.responses.SignInResponse;
import com.example.accountservice.utils.BcryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/v1/signup")
    public ResponseEntity<User> signUp(@RequestBody User user) {
        User result = userService.saveUser(user);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/api/v1/signin")
    public ResponseEntity<SignInResponse> signIn(@RequestBody SignInRequest user, HttpServletRequest request) throws IllegalArgumentException {
        User result = userService.getUser(user.getUsername());
        if (!BcryptUtil.validate(user.getPassword(), result.getPassword())) {
            throw new IllegalArgumentException("Cannot logged in with this username/password");
        }
        SignInResponse r = new SignInResponse(jwtService.generateToken(result.getUsername(), result.getRole().toString(), request));
        return new ResponseEntity<>(r, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/v1/authenticate/{token}")
    public ResponseEntity<AuthenticateResponse> authenticate(@PathVariable String token) {
        boolean status = jwtService.verifyToken(token);
        return new ResponseEntity<>(new AuthenticateResponse(token, status), HttpStatus.OK);
    }
}