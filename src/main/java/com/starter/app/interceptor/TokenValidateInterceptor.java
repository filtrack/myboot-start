package com.starter.app.interceptor;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.starter.app.config.GlobalParams;
import com.starter.app.dto.AccountToken;
import com.starter.app.service.JWTService;
import com.starter.app.service.RedisService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
@Slf4j
public class TokenValidateInterceptor extends HandlerInterceptorAdapter {

    final RedisService redisService;
    final JWTService jwtService;
    final GlobalParams globalParams;

    public TokenValidateInterceptor(RedisService redisService,
                                    JWTService jwtService,
                                    GlobalParams globalParams) {
        this.redisService = redisService;
        this.jwtService = jwtService;
        this.globalParams = globalParams;
    }

    @SneakyThrows
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        request.setCharacterEncoding("UTF-8");
        log.info("请求地址: {} {}", request.getRequestURL().toString(), request.getMethod());

        String jwtToken = request.getHeader("jwtToken");
        Assert.notNull(jwtToken,"jwtToken is null");

        Map<String, Claim> claimMap =  jwtService.verifyToken(jwtToken);
        String accountId = claimMap.get("payload").asString();
        String tokenId = claimMap.get("jti").asString();
        String tokenKey = "token:" + accountId;
        AccountToken ac = (AccountToken) redisService.get(tokenKey);

        if (ac != null){
            //续约
            if(ac.getTokenId().equals(tokenId) && Long.parseLong(accountId)==ac.getAccountId()){
                redisService.set("token:" + accountId, ac,globalParams.getExpire());
                return true;
            }else{
                throw new JWTVerificationException("token验证失效，请重新获取token");
            }

        }else{
            throw new JWTVerificationException("请先获取token");
        }
    }

}
