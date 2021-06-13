package com.cognizant.authorizationmicroservice.services.authorization;

import java.util.Optional;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cognizant.authorizationmicroservice.models.User;
import com.cognizant.authorizationmicroservice.repository.UserRepo;
import com.cognizant.authorizationmicroservice.services.authorization.exceptions.InvalidToken;
import com.cognizant.authorizationmicroservice.services.jwt.TokenVerification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class Authorizer {

    @Autowired
    UserRepo userRepo;

    @Autowired
    TokenVerification tokenVerification;

    public static final Logger LOGGER = LoggerFactory.getLogger(Authorizer.class);

    public boolean isValid(String token) throws InvalidToken{
        
        DecodedJWT jwt = JWT.decode(token);
        Long userId = Long.parseLong(jwt.getKeyId());
        Optional<User> user = userRepo.findById(userId);
        if(user.isPresent() && user.get().getUserName().equals(jwt.getSubject())){
            if(tokenVerification.verifyToken(token, user.get().getUserId().toString(), user.get().getUserName())){
                return true;
            }
            
        }
        else{
            throw new InvalidToken("Token is invalid");
        }
        return false;
    }
}
