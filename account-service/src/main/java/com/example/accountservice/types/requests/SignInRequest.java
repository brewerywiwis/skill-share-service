package com.example.accountservice.types.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class SignInRequest {
    @Getter
    private final String username;
    @Getter
    private final String password;
}