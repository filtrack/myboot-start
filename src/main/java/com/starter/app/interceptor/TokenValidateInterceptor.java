package com.starter.app.interceptor;

import com.auth0.jwt.interfaces.Claim;
import com.starter.app.dto.AccountToken;
import com.starter.app.service.RedisService;
import com.starter.app.utils.JWTUtils;
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

    public TokenValidateInterceptor(RedisService redisService) {
        this.redisService = redisService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.info("请求地址: {} {}", request.getRequestURL().toString(), request.getMethod());

        String jwtToken = request.getHeader("jwtToken");
        Assert.notNull(jwtToken,"jwtToken is null");

        Map<String, Claim> claimMap =  JWTUtils.verifyToken(jwtToken);
        String accountId = claimMap.get("payload").asString();
        String tokenKey = "token:" + accountId;
        AccountToken ac = (AccountToken) redisService.get(tokenKey);

        if (ac != null  && Long.parseLong(accountId)==ac.getAccountId()){
            //续约
            redisService.set("token:" + accountId, ac,180);
            return true;
        }


        return false;
    }
}
