package com.starter.app;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JWTTest {

    public static void main(String[] args) {
        Algorithm algorithm = Algorithm.HMAC256("secret");
        String token = JWT.create()
                .withIssuer("auth0")
                .sign(algorithm);
        System.out.println(token);


        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("auth0")
                .build(); //Reusable verifier instance
        DecodedJWT jwt = verifier.verify(token);
        System.out.println(jwt.getHeader() + "\n" + jwt.getSignature() + "\n" + jwt.getPayload() + "\n" + jwt.getToken() + "\n" + jwt.getAlgorithm() + "\n" + jwt.getId() + "\n" + jwt.getIssuer());
    }
}
