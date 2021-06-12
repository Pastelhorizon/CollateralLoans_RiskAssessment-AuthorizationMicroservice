package com.cognizant.authorizationmicroservice.services.authentication.exceptions;

public class InvalidCredentials extends Exception {
    
    public InvalidCredentials(String msg){
        super(msg);
    }
}
