package com.cognizant.authorizationmicroservice.services.jwt;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.cognizant.authorizationmicroservice.util.DateUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenGenerator {

    @Value("${sign.secret}")
    private String secret;

    @Value("${sign.issuer}")
    private String issuer;

    @Autowired
    DateUtil dateUtil;

    public String generateToken(String userName, String userId) {
        String token = ""; 
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            token = JWT.create()
                        .withKeyId(userId)
                        .withIssuer(issuer)
                        .withSubject(userName)
                        .withIssuedAt(new Date())
                        .withExpiresAt(dateUtil.addMinutes(new Date(), 10))
                        .sign(algorithm);
        } catch (JWTCreationException e) {
            //Invalid Signing configuration / Couldn't convert Claims.
        }
        return token;
    }
}
