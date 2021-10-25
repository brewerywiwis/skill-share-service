package com.example.accountservice.types.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SearchUserRequest {
    private String username;
    private String fname;
    private String lname;
    private String tel;
    private String email;
    private String role;
    private String active;
}