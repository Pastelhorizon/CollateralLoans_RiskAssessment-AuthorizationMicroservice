package com.cognizant.authorizationmicroservice.services.authentication;

import com.cognizant.authorizationmicroservice.services.UserVerification;
import com.cognizant.authorizationmicroservice.services.authentication.exceptions.InvalidCredentials;
import com.cognizant.authorizationmicroservice.services.jwt.TokenGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Authenticator {

    @Autowired
    UserVerification userVerification;

    @Autowired
    TokenGenerator tokenGenerator;
    public String authenticate(String userName, String password) throws InvalidCredentials{
        Long userId = userVerification.verifyUser(userName, password);
        if(userId != null){
            return tokenGenerator.generateToken(userName, Long.toString(userId));
        }
        else{
            return "";
        }
    }
}
