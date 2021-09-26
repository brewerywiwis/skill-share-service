package com.example.accountservice.types.responses;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AuthenticateResponse {
    private String token;
    private boolean status;
    private LocalDateTime time;

    public AuthenticateResponse(String token, boolean status) {
        this.token = token;
        this.status = status;
        this.time = LocalDateTime.now();
    }
}