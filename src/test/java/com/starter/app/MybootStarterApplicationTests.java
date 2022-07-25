package com.starter.app;

import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSON;
import com.starter.app.dto.LaunageDto;
import com.starter.app.dto.TopicDto;
import com.starter.app.entity.Launage;
import com.starter.app.service.LaunageService;
import com.starter.app.service.TopicService;
import com.starter.app.service.impl.LaunageServiceImpl;
import com.starter.app.vo.PageVo;
import com.starter.app.dto.UserDto;
import com.starter.app.result.CommonResult;
import com.starter.app.service.UserService;
import com.starter.app.vo.UserVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class MybootStarterApplicationTests {

    @Autowired
    UserService userService;

    @Autowired
    TopicService topicService;

    @Autowired
    LaunageService launageService;

    public static void main(String[] args) {
        System.out.println(IdUtil.getSnowflake().nextIdStr());
    }

    @Test
    void addUser() {
        UserDto userDto = new UserDto();
        userDto.setUsername("houjiale_123");
        userDto.setPassword(SecureUtil.md5("123456"));
        userDto.setIntro("我是一个活泼爱动的活力宝宝~~");
        userDto.setSex(0);
        userDto.setBirthday("2016-12-12");
        userDto.setNickname("侯嘉乐");
        Boolean bool = userService.addUser(userDto);
        System.out.println(CommonResult.success(bool));
    }

    @Test
    void addLaunage(){
        List<LaunageDto> ls = Arrays.asList(new LaunageDto("java","面向对象开发语言"),new LaunageDto("golang","高并发服务端开发"));
        Boolean bool = launageService.addLaunage(ls.get(0));
        System.out.println(CommonResult.success(bool));
        bool = launageService.addLaunage(ls.get(1));
        System.out.println(CommonResult.success(bool));
    }

    @Test
    void addTopic(){
        String lid= "1551583015916720130";
        String uid = "1551502630400090112";
        TopicDto topicDto = new TopicDto(lid,uid,"SpringBoot 深入学习","学习SpringBoot精髓");
        Boolean bool = topicService.addTopic(topicDto);
        System.out.println(CommonResult.success(bool));
        topicDto = new TopicDto(lid,uid,"SpringCloud 教程","适合新手学习的SpringCloud课程");
        bool = topicService.addTopic(topicDto);
        System.out.println(CommonResult.success(bool));
    }

}
