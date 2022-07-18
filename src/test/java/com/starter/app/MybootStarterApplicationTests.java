package com.starter.app;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.starter.app.dto.PageVo;
import com.starter.app.entity.Person;
import com.starter.app.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MybootStarterApplicationTests {

    @Autowired
    PersonService personService;


    @Test
    void contextLoads() {
        personService.add(new Person("hjw",1));
    }

    @Test
    void contextLoads2() {
        PageVo page = personService.queryPage(null);
        System.out.println(JSON.toJSONString(page));
    }

}
