package com.example.accountservice.types;

import lombok.Data;

import java.util.List;

@Data
public class TokenPayload {
    private String username;
    private List<String> role;

    public TokenPayload(String username, List<String> role) {
        this.username = username;
        this.role = role;
    }
}