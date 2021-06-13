package com.cognizant.authorizationmicroservice.services;

import java.util.List;

import com.cognizant.authorizationmicroservice.services.jwt.TokenGenerator;
import com.cognizant.authorizationmicroservice.models.User;
import com.cognizant.authorizationmicroservice.repository.UserRepo;
import com.cognizant.authorizationmicroservice.services.authentication.exceptions.InvalidCredentials;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserVerification {
    
    @Autowired
    UserRepo userRepo;

    @Autowired
    TokenGenerator tokenGenerator;

    public String verifyUser(String userName, String password) throws InvalidCredentials{
        List<User> usersList = userRepo.findByUserName(userName);
        if(usersList.isEmpty()){
            throw new InvalidCredentials(String.format("User: %s NOT FOUND", userName));
        }
        User user = usersList.get(0);
        if(user.getPassword().equals(password)){
            return user.getUserId();
        }
        else{
            throw new InvalidCredentials("Wrong Password");
        }
    }
}
