package com.starter.app.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.starter.app.config.GlobalParams;
import com.starter.app.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class JWTServiceImpl implements JWTService {

    final GlobalParams globalParams;

    public JWTServiceImpl(GlobalParams globalParams) {
        this.globalParams = globalParams;
    }

    @Override
    public String createToken(String payload, String tokenId) {
        System.out.println(globalParams.getSecret());
        Algorithm algorithm = Algorithm.HMAC256(globalParams.getSecret());
        JWTCreator.Builder builder = JWT.create();
        builder.withNotBefore(new Date());
        builder.withClaim("payload",payload);
        builder.withJWTId(tokenId);
        return builder.sign(algorithm);
    }

    @Override
    public Map<String, Claim> verifyToken(String jwtToken) {
        Algorithm algorithm = Algorithm.HMAC256(globalParams.getSecret());
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(jwtToken);
        return decodedJWT.getClaims();
    }
}
