//package com.example.accountservice.services.authentication;
//
//import com.example.accountservice.entities.User;
//import com.example.accountservice.repositories.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
//@Service
//public class AuthenticationService {
//
//    private final UserRepository userRepository;
//
//    @Autowired
//    public AuthenticationService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @PostMapping("/api/v1/signup")
//    public ResponseEntity<User> signUp(@RequestBody User user) {
//        User result = userRepository.save(user);
//        return new ResponseEntity<>(result, HttpStatus.CREATED);
//    }
//}