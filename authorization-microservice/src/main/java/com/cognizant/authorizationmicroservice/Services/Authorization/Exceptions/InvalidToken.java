package com.cognizant.authorizationmicroservice.Services.Authorization.Exceptions;

public class InvalidToken extends Exception{

    public InvalidToken(String msg){
        super(msg);
    }
    
}
