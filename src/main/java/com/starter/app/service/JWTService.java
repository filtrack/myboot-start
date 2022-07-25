package com.starter.app.service;

import com.auth0.jwt.interfaces.Claim;

import java.util.Map;

public interface JWTService {
    String createToken(String payload);
    Map<String, Claim> verifyToken(String jwtToken);

}
