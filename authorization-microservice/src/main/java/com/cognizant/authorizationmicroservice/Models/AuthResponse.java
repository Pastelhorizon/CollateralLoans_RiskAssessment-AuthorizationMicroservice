package com.cognizant.authorizationmicroservice.models;

import lombok.Data;

@Data
public class AuthResponse {
    String token;

    public AuthResponse(String token) {
        this.token = token;
    }
}
