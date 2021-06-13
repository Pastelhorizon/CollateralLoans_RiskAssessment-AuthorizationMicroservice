package com.cognizant.authorizationmicroservice.repository;

import java.util.List;

import com.cognizant.authorizationmicroservice.models.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User,Long> {

    List<User> findByUserName(String userName);
}
