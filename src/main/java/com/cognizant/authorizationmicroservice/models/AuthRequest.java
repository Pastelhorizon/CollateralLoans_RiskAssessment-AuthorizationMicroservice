package com.cognizant.authorizationmicroservice.models;

//import lombok.Data;

//@Data
public class AuthRequest {
    
 
    public AuthRequest() {
    	super();
    }
    public AuthRequest(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	String userName;
    String password;
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
