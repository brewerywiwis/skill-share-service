package com.example.accountservice.types.requests;

import lombok.Data;

@Data
public class AuthenticateRequest {
    private String token;
    private String username;
}