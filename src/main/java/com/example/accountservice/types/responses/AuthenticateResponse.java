package com.example.accountservice.types.responses;

import com.example.accountservice.types.TokenPayload;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AuthenticateResponse {
    private String token;
    private boolean status;
    private TokenPayload user;
    private LocalDateTime time;

    public AuthenticateResponse(String token, boolean status, TokenPayload user) {
        this.token = token;
        this.status = status;
        this.user = user;
        this.time = LocalDateTime.now();
    }
}