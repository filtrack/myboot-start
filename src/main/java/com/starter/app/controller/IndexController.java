package com.starter.app.controller;

import com.starter.app.annotation.Log;
import com.starter.app.dto.PageVo;
import com.starter.app.dto.Student;
import com.starter.app.dto.UserDto;
import com.starter.app.result.CommonResult;
import com.starter.app.service.UserService;
import com.starter.app.validation.ValidGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class IndexController {

    @Autowired
    UserService userService;

    /**
     * 用户列表
     * @param  userDto 用户
     * @return
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
     * @return
     */
    @Log("请求用户详情")
    @PostMapping("/user/{id}")
    public Object user(@PathVariable("id") Long id){
        UserDto userDto = userService.findUserById(id);
        return CommonResult.success(userDto);
    }

}
