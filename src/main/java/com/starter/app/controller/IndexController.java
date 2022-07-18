package com.starter.app.controller;

import com.starter.app.dto.Student;
import com.starter.app.result.CommonResult;
import com.starter.app.validation.Crud;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class IndexController {

    /**
     * 最简单的测试
     * @param userName 用户
     * @return
     */
    @GetMapping("/hello")
    public Object getStr(String userName){
        return CommonResult.success(userName);
    }


    /**
     * 这个是错误接口测试吗
     * @param student 校验
     * @return
     */
    @GetMapping("/wrong")
    public Object wrong(@RequestBody @Validated(value = {Crud.Create.class})  Student student){
        int i = 9/0;
        return CommonResult.success(i);
    }

    /**
     *
     * @param student1 校验
     * @return
     */
    @GetMapping("/obj")
    public Object obj(@Validated(value = {Crud.Update.class})  Student student1){
        Student student = new Student("1","javadaily","jianzh5@163.com");
        Student student2 = new Student("1","javadaily22","jianzh5@163.com");
        return CommonResult.success(Stream.of(student,student2).collect(Collectors.toList()));
    }
}
