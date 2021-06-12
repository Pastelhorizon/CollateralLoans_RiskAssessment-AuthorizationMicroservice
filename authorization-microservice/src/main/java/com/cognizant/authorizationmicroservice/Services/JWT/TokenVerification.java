package com.cognizant.authorizationmicroservice.Services.JWT;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cognizant.authorizationmicroservice.Services.Authorization.Exceptions.InvalidToken;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
@Service
public class TokenVerification {

    public static final Logger LOGGER = LoggerFactory.getLogger(TokenVerification.class);
    @Value("${sign.secret}")
    private String SECRET;

    @Value("${sign.issuer}")
    private String ISSUER;

    public boolean verifyToken(String token, String userId, String userName) throws InvalidToken{

        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                                    .withIssuer(ISSUER)
                                    .withSubject(userName)
                                    .build();
            // @SuppressWarnings
            DecodedJWT tokenJwt = JWT.decode(token);
            LOGGER.info(String.format("Token ID: %s , User ID: %s", tokenJwt.getKeyId(), userId));
            LOGGER.info(String.format("Token Subject: %s , UserName: %s", tokenJwt.getSubject(), userName));
            
            DecodedJWT jwt = verifier.verify(token);
        } catch (JWTVerificationException e) {
            LOGGER.info(e.toString());
            throw new InvalidToken(e.getMessage());
        }
        return true;
    }
}
