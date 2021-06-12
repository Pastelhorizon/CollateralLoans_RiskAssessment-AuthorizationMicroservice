package com.cognizant.authorizationmicroservice.Services.Authorization;

import java.util.Optional;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cognizant.authorizationmicroservice.Models.User;
import com.cognizant.authorizationmicroservice.Repository.UserRepo;
import com.cognizant.authorizationmicroservice.Services.Authorization.Exceptions.InvalidToken;
import com.cognizant.authorizationmicroservice.Services.JWT.TokenVerification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class Authorization {

    @Autowired
    UserRepo userRepo;

    @Autowired
    TokenVerification tokenVerification;

    public static final Logger LOGGER = LoggerFactory.getLogger(Authorization.class);

    public boolean isValid(String token) throws InvalidToken{
        
        DecodedJWT jwt = JWT.decode(token);
        String userId = jwt.getKeyId();
        LOGGER.info(String.format("User ID from the token : %s", userId));
        Optional<User> user = userRepo.findById(userId);
        LOGGER.info(String.format("User ID from the userRepo : %s", user.get().getUserId()));
        if(user.isPresent() && user.get().getUserName().equals(jwt.getSubject())){
            LOGGER.info(String.format("UserNames are same"));
            if(tokenVerification.verifyToken(token, user.get().getUserId(), user.get().getUserName())){
                return true;
            }
            
        }
        else{
            throw new InvalidToken("Token is invalid");
        }
        return false;
    }
}
