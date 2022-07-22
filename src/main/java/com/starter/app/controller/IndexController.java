package com.starter.app.controller;

import com.starter.app.annotation.Log;
import com.starter.app.dto.PageVo;
import com.starter.app.dto.UserDto;
import com.starter.app.result.CommonResult;
import com.starter.app.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class IndexController {

    final UserService userService;

    public IndexController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 用户列表
     * @param userDto 用户
     * @return CommonResult
     */
    @Log("请求用户列表")
    @PostMapping("/users")
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
    @PostMapping("/user/{id}")
    public Object user(@PathVariable("id") Long id){
        UserDto userDto = userService.findUserById(id);
        Assert.notNull(userDto, "用户不存在！");
        return CommonResult.success(userDto);
    }

}
