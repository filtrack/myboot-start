package com.starter.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.starter.app.dto.MyPage;
import com.starter.app.dto.PageVo;
import com.starter.app.dto.UserDto;
import com.starter.app.entity.User;
import com.starter.app.mapper.UserMapper;
import com.starter.app.service.UserService;
import com.starter.app.utils.OrikaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * 服务接口实现
 */

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
    public PageVo<UserDto> queryPage(UserDto dto) {
        LinkedHashMap orderMap = new LinkedHashMap();
        orderMap.put("id","asc");
        Page<User> page = MyPage.of(dto.getPage(),dto.getSize(),orderMap);
        QueryWrapper queryWrapper = new QueryWrapper();
        userMapper.selectPage(page,queryWrapper);
        return OrikaUtils.convertPageVo(page,UserDto.class);
    }

    @Override
    public UserDto findUserById(Long id) {
        User user = userMapper.selectById(id);
        return OrikaUtils.convert(user,UserDto.class);
    }

    @Override
    public User findByUserName(String username) {
        return userMapper.findByUserName(username);
    }
}