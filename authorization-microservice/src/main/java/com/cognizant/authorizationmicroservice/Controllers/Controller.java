package com.cognizant.authorizationmicroservice.Controllers;

import com.cognizant.authorizationmicroservice.Exceptions.InvalidCredentials;
import com.cognizant.authorizationmicroservice.Models.AuthRequest;
import com.cognizant.authorizationmicroservice.Models.AuthResponse;
import com.cognizant.authorizationmicroservice.Services.Authentication.Authenticator;
import com.cognizant.authorizationmicroservice.Services.Authorization.Authorization;
import com.cognizant.authorizationmicroservice.Services.Authorization.Exceptions.InvalidToken;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authapp")
public class Controller {

    
    @Autowired
    Authenticator authenticator;

    @Autowired
    Authorization authorization;

    public static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);

    @RequestMapping(value = "/checkService", method = RequestMethod.GET)
    public ResponseEntity<?> checkService() {
        String success = "Authorization Service Running Successfully";
        LOGGER.debug(success);
        return new ResponseEntity<>(success, HttpStatus.OK);
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> authenticate(@RequestBody AuthRequest authRequest) throws Exception {
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

    @RequestMapping(value = "/authorize", method = RequestMethod.GET)
    public ResponseEntity<Boolean> authorize(@RequestHeader(value = "Authorization") String token) {

        try {
            LOGGER.info(String.format("Jwt Token: %s", token.substring(7)));
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
