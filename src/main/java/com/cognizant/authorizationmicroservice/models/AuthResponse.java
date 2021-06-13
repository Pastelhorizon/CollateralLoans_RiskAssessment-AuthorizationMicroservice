package com.cognizant.authorizationmicroservice.models;

//import lombok.Data;

//@Data
public class AuthResponse {
    public AuthResponse(String token) {
		super();
		this.token = token;
	}

	String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public AuthResponse() {
		super();
	}

    
}
