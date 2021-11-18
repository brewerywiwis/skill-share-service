package com.example.accountservice.types.requests;

import lombok.Data;
@Data
public class EditUserRequest {
    private String password;
    private String fname;
    private String lname;
    private String tel;
    private String email;
}