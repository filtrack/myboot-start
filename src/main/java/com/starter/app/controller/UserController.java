package com.starter.app.controller;

import com.starter.app.annotation.Log;
import com.starter.app.annotation.RateLimiter;
import com.starter.app.config.GlobalConst;
import com.starter.app.dto.LoginDto;
import com.starter.app.dto.PageVo;
import com.starter.app.dto.UserDto;
import com.starter.app.result.CommonResult;
import com.starter.app.service.JWTService;
import com.starter.app.service.RedisService;
import com.starter.app.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Slf4j
@Valid
@RestController
@RequestMapping("/user")
public class UserController {

    final UserService userService;
    final RedisService redisService;
    final JWTService jwtService;
    final GlobalConst globalParams;

    public UserController(UserService userService,
                          RedisService redisService,
                          JWTService jwtService,
                          GlobalConst globalParams) {
        this.userService = userService;
        this.redisService = redisService;
        this.jwtService = jwtService;
        this.globalParams = globalParams;
    }

    /**
     * 用户登录
     *
     * @param username  用户名
     * @param password 账户密码
     * @return CommonResult
     */
    @Log("账户登陆")
    @RateLimiter(time = 5,count = 3,limitType = RateLimiter.LimitType.IP)
    @PostMapping("/login")
    public Object login(@NotBlank String username, @NotBlank String password) {
        LoginDto loginDto = userService.login(username,password);
        return CommonResult.success(loginDto,"login successful");
    }

    /**
     * 用户列表
     * @param userDto 用户
     * @return CommonResult
     */
    @Log("请求用户列表")
    @PostMapping("/list")
    public Object users(UserDto userDto){
        PageVo<UserDto> pageResult =  userService.queryPage(userDto);
        return CommonResult.success(pageResult);
    }

    /**
     * 用户列表
     * @param  id 用户
     * @return CommonResult
     */
    @Log("请求用户详情")
    @GetMapping("/{id}")
    public Object user(@PathVariable("id") Long id){
        UserDto userDto = userService.findUserById(id);
        Assert.notNull(userDto, "用户不存在！");
        return CommonResult.success(userDto);
    }




}
