package com.starter.app.utils;

import cn.hutool.core.util.IdUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.starter.app.config.GlobalConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class JWTUtils {

    @Autowired
    static
    GlobalConst globalParams;

    private static String secret = "myboot-starter";

    public static String createToken(String payload, String tokenId){
        System.out.println(globalParams.getSecret());
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTCreator.Builder builder = JWT.create();
        builder.withNotBefore(new Date());
        builder.withClaim("payload",payload);
        builder.withJWTId(tokenId);
        return builder.sign(algorithm);
    }

    public static Map<String,Claim> verifyToken(String jwtToken)throws JWTVerificationException{
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(jwtToken);
        return decodedJWT.getClaims();
    }

    public static void main(String[] args) {
        Map<String,Object> claimMap = new HashMap<>();
        String userId = "1001";
        String token = createToken(userId, IdUtil.getSnowflake().nextIdStr());
        log.info("token:{}",token);
        Map<String,Claim> resultMap = verifyToken(token);
        resultMap.forEach((k,v)->{
            log.info(k+":"+v.toString());
        });
    }
}
