package com.example.accountservice.controllers;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @GetMapping("/api/v1/health")
    public HttpEntity<String> getHealthServer() {
        return new ResponseEntity<>("SERVICE AVAILABLE", HttpStatus.OK);
    }

}