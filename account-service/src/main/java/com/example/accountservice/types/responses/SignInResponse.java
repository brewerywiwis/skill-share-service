package com.example.accountservice.types.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class SignInResponse {
    @Getter
    @Setter
    private String token;
}