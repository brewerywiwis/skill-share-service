package com.example.accountservice.types.responses;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorResponse {

    private LocalDateTime time;
    private String message;

    public ErrorResponse(String message) {
        this.time = LocalDateTime.now();
        this.message = message;
    }
}