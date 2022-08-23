package com.starter.app.controller;

import com.starter.app.aspectj.annotation.Log;
import com.starter.app.aspectj.annotation.RateLimiter;
import com.starter.app.config.GlobalConst;
import com.starter.app.domain.dto.UserDTO;
import com.starter.app.domain.result.CommonResult;
import com.starter.app.service.JWTService;
import com.starter.app.service.RedisService;
import com.starter.app.service.UserService;
import com.starter.app.domain.vo.LoginVO;
import com.starter.app.domain.vo.PageVO;
import com.starter.app.domain.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

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
        LoginVO loginVo = userService.login(username,password);
        return CommonResult.success(loginVo,"login successful");
    }

    /**
     * 用户列表
     * @param userDto 用户
     * @return CommonResult
     */
    @Log("用户列表")
    @RateLimiter(time = 5,count = 3,limitType = RateLimiter.LimitType.IP)
    @PostMapping("/list")
    public Object users(UserDTO userDto){
        PageVO<UserVO> pageResult =  userService.queryPage(userDto);
        return CommonResult.success(pageResult);
    }

    /**
     * 用户详情
     * @param  id 用户
     * @return CommonResult
     */
    @Log("用户详情")
    @RateLimiter(time = 5,count = 3,limitType = RateLimiter.LimitType.IP)
    @GetMapping("/{id}")
    public Object user(@PathVariable("id") Long id){
        UserVO userVO = userService.findById(id);
        Assert.notNull(userVO, "用户不存在！");
        return CommonResult.success(userVO);
    }




}
