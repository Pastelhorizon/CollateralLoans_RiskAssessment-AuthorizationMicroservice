package com.cognizant.authorizationmicroservice.Repository;

import java.util.List;

import com.cognizant.authorizationmicroservice.Models.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User,String> {

    List<User> findByUserName(String userName);
}
