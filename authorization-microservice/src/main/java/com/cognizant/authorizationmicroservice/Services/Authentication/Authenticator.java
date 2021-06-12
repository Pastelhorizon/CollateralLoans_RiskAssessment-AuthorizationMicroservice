package com.cognizant.authorizationmicroservice.Services.Authentication;

import com.cognizant.authorizationmicroservice.Exceptions.InvalidCredentials;
import com.cognizant.authorizationmicroservice.Services.UserVerification;
import com.cognizant.authorizationmicroservice.Services.JWT.TokenGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Authenticator {

    @Autowired
    UserVerification userVerification;

    @Autowired
    TokenGenerator tokenGenerator;
    public String authenticate(String userName, String password) throws InvalidCredentials{
        String userId = userVerification.verifyUser(userName, password);
        if(!(userId.isEmpty())){
            return tokenGenerator.generateToken(userName, userId);
        }
        else{
            return "";
        }
    }
}
