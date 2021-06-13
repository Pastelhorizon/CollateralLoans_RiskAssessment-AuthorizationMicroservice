package com.cognizant.authorizationmicroservice.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//import lombok.Data;
//@Data
@Entity
public class User {
    public User() {
		super();
	}
	public User(Long userId, String userName, String password) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
	}
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long userId;
	@Column(nullable = false)
	String userName;
	@Column
	String password;
    public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
    
}
