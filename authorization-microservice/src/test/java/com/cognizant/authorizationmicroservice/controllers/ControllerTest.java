package com.cognizant.authorizationmicroservice.controllers;

import com.cognizant.authorizationmicroservice.models.AuthRequest;
import com.cognizant.authorizationmicroservice.models.AuthResponse;
import com.cognizant.authorizationmicroservice.services.jwt.TokenGenerator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ControllerTest {

    @Autowired
    Controller controller;

    @Autowired
    TokenGenerator tokenGenerator;


    @Test
    void testAuthenticate() {
        // assertEquals(new ResponseEntity(new AuthResponse(tokenGenerator.generateToken("admin1", "1")), HttpStatus.OK),controller.authenticate(new AuthRequest("admin1", "admin1")));
    }

    @Test
    void testAuthorize() {

    }

    @Test
    void testCheckService() {

    }
}
