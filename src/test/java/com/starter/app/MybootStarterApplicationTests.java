package com.starter.app;

import com.alibaba.fastjson.JSON;
import com.starter.app.dto.PageVo;
import com.starter.app.dto.UserDto;
import com.starter.app.entity.User;
import com.starter.app.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MybootStarterApplicationTests {

    @Autowired
    UserService userService;

    @Test
    void addUser() {
        userService.add(new User("hjw",1,"houjingwei@163.com"));
    }

    @Test
    void pageUsers() {
        PageVo<UserDto> page = userService.queryPage(null);
        System.out.println(JSON.toJSONString(page));
    }
}
