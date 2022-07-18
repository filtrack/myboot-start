package com.starter.app.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.starter.app.dto.PageVo;
import com.starter.app.dto.Student;
import com.starter.app.dto.Teacher;
import com.starter.app.dto.TeacherGrade;
import com.starter.app.entity.Person;
import com.starter.app.mapper.PersonMapper;
import com.starter.app.service.PersonService;
import com.starter.app.utils.OrikaUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * 服务接口实现
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class PersonServiceImpl implements PersonService {
    @Autowired PersonMapper personMapper;

    @Override
    public boolean add(Person person) {
        personMapper.insert(person);
        return true;
    }

    @Override
    public PageVo<Person> queryPage(Map map) {
        Page<Person> page = new Page<>(1,1);
        QueryWrapper queryWrapper = new QueryWrapper();
        personMapper.selectPage(page,queryWrapper);
        System.out.println(JSON.toJSONString(page));
        PageVo<Person> vo = OrikaUtils.convert(page, PageVo.class);
        return vo;
    }


}