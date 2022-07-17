package com.starter.app.controller;

import com.starter.app.result.CommonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class IndexController {

    @GetMapping("/hello")
    public String getStr(){
        return "hello,javadaily";
    }

    @GetMapping("/wrong")
    public int wrong(){
        int i = 9/0;
        return i;
    }

    @GetMapping("/obj")
    public Object obj(){
        Map<String,Object> map = new HashMap<>();
        map.put("username","hjw");
        map.put("age",32);
        return map;
    }
}
