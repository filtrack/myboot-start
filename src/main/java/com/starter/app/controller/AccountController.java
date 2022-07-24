package com.starter.app.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.starter.app.annotation.Log;
import com.starter.app.config.GlobalParams;
import com.starter.app.dto.AccountToken;
import com.starter.app.dto.PageVo;
import com.starter.app.dto.UserDto;
import com.starter.app.result.CommonResult;
import com.starter.app.service.JWTService;
import com.starter.app.service.RedisService;
import com.starter.app.service.UserService;
import com.starter.app.utils.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Slf4j
@RestController
public class AccountController {

    final UserService userService;
    final RedisService redisService;
    final JWTService jwtService;
    final GlobalParams globalParams;

    public AccountController(UserService userService,
                             RedisService redisService,
                             JWTService jwtService,
                             GlobalParams globalParams) {
        this.userService = userService;
        this.redisService = redisService;
        this.jwtService = jwtService;
        this.globalParams = globalParams;
    }

    /**
     * 账户登陆
     *
     * @param account  账户名
     * @param password 账户密码
     * @return CommonResult
     */
    @Log("账户登陆")
    @PostMapping("/login")
    public Object login(HttpServletRequest request, HttpServletResponse response, String account, String password) {
        AccountToken accountToken = new AccountToken();

        accountToken.setAccountId(1);
        accountToken.setAccount(account);
        accountToken.setPassword(password);
        // 随机生成uuid，作为token的id
        String tokenId = IdUtil.getSnowflake().nextIdStr();
        accountToken.setTokenId(tokenId);
        accountToken.setIssuedAt(System.currentTimeMillis());
        accountToken.setExpiresAt(DateUtil.offsetMinute(new Date(), 3).getTime());
        accountToken.setRemember(true);
        accountToken.setAgent(request.getHeader(HttpHeaders.USER_AGENT));
        accountToken.setIp(request.getRemoteAddr());

        //缓存accountToken
        redisService.set("token:" + accountToken.getAccountId(), accountToken,globalParams.getExpire());


        String jwtToken = jwtService.createToken(accountToken.getAccountId().toString(),tokenId);
        response.addHeader("jwtToken",jwtToken);
        return CommonResult.success("login successful");
    }


}
