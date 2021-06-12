package com.cognizant.authorizationmicroservice.services.authorization.exceptions;

public class InvalidToken extends Exception{

    public InvalidToken(String msg){
        super(msg);
    }
    
}
