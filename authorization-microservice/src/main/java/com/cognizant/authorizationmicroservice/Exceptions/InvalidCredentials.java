package com.cognizant.authorizationmicroservice.Exceptions;

public class InvalidCredentials extends Exception {
    
    public InvalidCredentials(String msg){
        super(msg);
    }
}
