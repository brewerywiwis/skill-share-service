package com.example.accountservice.types.requests;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class GetUserByIdList {
    private List<UUID> idList;
}