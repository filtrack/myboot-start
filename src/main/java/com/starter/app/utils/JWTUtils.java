package com.starter.app.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JWTUtils {

    private static String secret = "myboot-starter";

    public static String createToken(String payload ){
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTCreator.Builder builder = JWT.create();
        builder.withNotBefore(new Date());
//        builder.withJWTId(IdUtil.getSnowflake().nextIdStr());
        builder.withClaim("payload",payload);
        return builder.sign(algorithm);
    }

    @SneakyThrows
    public static Map<String,Claim> verifyToken(String jwtToken){
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        try {
            DecodedJWT decodedJWT = jwtVerifier.verify(jwtToken);
            return decodedJWT.getClaims();
        }catch (JWTDecodeException e){
            log.error("JWT解码异常");
            throw new Exception(e);
        }catch (SignatureVerificationException e){
            log.error("签名验证异常");
            throw new Exception(e);
        }catch (Exception e){
            log.error("校验令牌失败");
            throw new Exception(e);
        }
    }

    public static void main(String[] args) {
        Map<String,Object> claimMap = new HashMap<>();
        String userId = "1001";
        String token = createToken(userId);
        log.info("token:{}",token);
        Map<String,Claim> resultMap = verifyToken(token);
        resultMap.forEach((k,v)->{
            log.info(k+":"+v.toString());
        });
    }
}
