package com.cognizant.authorizationmicroservice.controllers;

import java.util.ArrayList;
import java.util.List;

import com.cognizant.authorizationmicroservice.models.User;
import com.cognizant.authorizationmicroservice.repository.UserRepo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddUserController {
    
    @Autowired
    UserRepo userRepo;
    public static final Logger LOGGER = LoggerFactory.getLogger(AddUserController.class);

    @PostMapping(path = "/addUser")
    public ResponseEntity<User> addUser(@RequestBody User userDetails) {

        userRepo.save(userDetails);
        User addedUser = userRepo.findByUserName(userDetails.getUserName()).get(0);
        String success = "User Added Successfully";
        LOGGER.debug(success);
        return new ResponseEntity<>(addedUser, HttpStatus.OK);
    }

    @GetMapping(path = "/listUsers")
    public ResponseEntity<List<User>> listUsers() {
        List<User> result = new ArrayList<>();
        userRepo.findAll().forEach(result::add);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
