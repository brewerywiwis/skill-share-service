package com.example.accountservice.types;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TokenPayload {
    private String uid;
    private String username;
    private List<String> roles;
}