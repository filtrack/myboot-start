package com.starter.app.service.impl;

import com.alibaba.druid.pool.ha.PropertiesUtils;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.starter.app.dto.PageVo;
import com.starter.app.dto.UserDto;
import com.starter.app.entity.Person;
import com.starter.app.entity.User;
import com.starter.app.mapper.PersonMapper;
import com.starter.app.mapper.UserMapper;
import com.starter.app.service.PersonService;
import com.starter.app.service.UserService;
import com.starter.app.utils.OrikaUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 服务接口实现
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public boolean add(User user) {
        userMapper.insert(user);
        return true;
    }

    @Override
    public PageVo<UserDto> queryPage(Map map) {

        QueryWrapper queryWrapper = new QueryWrapper();
        Page<User> page = userMapper.selectPage(Page.of(1,10),queryWrapper);

        return OrikaUtils.convertPageVo(page,UserDto.class);
    }


}