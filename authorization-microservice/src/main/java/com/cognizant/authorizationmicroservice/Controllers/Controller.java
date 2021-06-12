package com.cognizant.authorizationmicroservice.controllers;


import com.cognizant.authorizationmicroservice.models.AuthRequest;
import com.cognizant.authorizationmicroservice.models.AuthResponse;
import com.cognizant.authorizationmicroservice.services.authentication.Authenticator;
import com.cognizant.authorizationmicroservice.services.authentication.exceptions.InvalidCredentials;
import com.cognizant.authorizationmicroservice.services.authorization.Authorizer;
import com.cognizant.authorizationmicroservice.services.authorization.exceptions.InvalidToken;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authapp")
public class Controller {

    
    @Autowired
    Authenticator authenticator;

    @Autowired
    Authorizer authorization;

    public static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);

    @GetMapping(path = "/checkService")
    public ResponseEntity<String> checkService() {
        String success = "Authorization Service Running Successfully";
        LOGGER.debug(success);
        return new ResponseEntity<>(success, HttpStatus.OK);
    }

    @PostMapping(path = "/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthRequest authRequest) {
        String reqUserName = authRequest.getUserName();
        String reqPassword = authRequest.getPassword();
        String token = "";
        try {
            token = authenticator.authenticate(reqUserName, reqPassword);
        } catch (InvalidCredentials e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(new AuthResponse(token), HttpStatus.OK);
    }

    @GetMapping(path = "/authorize")
    public ResponseEntity<Boolean> authorize(@RequestHeader(value = "Authorization") String token) {

        try {
            LOGGER.info(String.format("Jwt Token Recieved: %s", token.substring(7)));
            if (authorization.isValid(token.substring(7))) {
                return new ResponseEntity<>(true, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(false, HttpStatus.FORBIDDEN);
            }
        } catch (InvalidToken e) {
            return new ResponseEntity<>(false, HttpStatus.FORBIDDEN);
        }

        
    }
}
