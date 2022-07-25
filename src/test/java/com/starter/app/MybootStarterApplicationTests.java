package com.starter.app;

import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSON;
import com.starter.app.dto.PageVo;
import com.starter.app.dto.UserDto;
import com.starter.app.entity.User;
import com.starter.app.result.CommonResult;
import com.starter.app.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MybootStarterApplicationTests {

    @Autowired
    UserService userService;

    public static void main(String[] args) {
        System.out.println(IdUtil.getSnowflake().nextIdStr());
    }

    @Test
    void addUser() {
        UserDto userDto = new UserDto();
        userDto.setId(IdUtil.getSnowflake().nextIdStr());
        userDto.setUsername("houjiale");
        userDto.setPassword(SecureUtil.md5("123456"));
        userDto.setIntro("我是一个活泼爱动的活力宝宝~~");
        userDto.setSex(0);
        userDto.setBirthday("2016-12-12");
        userDto.setNickname("侯嘉乐");
        Boolean bool = userService.addUser(userDto);
        System.out.println(CommonResult.success(bool));
    }

    @Test
    void pageUsers() {
        PageVo<UserDto> page = userService.queryPage(null);
        System.out.println(JSON.toJSONString(page));
    }
}
