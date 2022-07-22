package com.starter.app.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.starter.app.dto.MyPage;
import com.starter.app.dto.PageVo;
import com.starter.app.dto.UserDto;
import com.starter.app.entity.User;
import com.starter.app.mapper.UserMapper;
import com.starter.app.service.UserService;
import com.starter.app.utils.OrikaUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * 服务接口实现
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    /**
     * 基于构造器注入依赖，官方推荐
     */
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public void add(User user) {
        userMapper.insert(user);
    }

    @Override
    public PageVo<UserDto> queryPage(UserDto dto) {
        LinkedHashMap<String,String> orderMap = new LinkedHashMap<>();
        orderMap.put("id","asc");

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(ObjectUtil.isNotEmpty(dto.getName()),"name",dto.getName());
        Page<User> page = MyPage.of(dto.getPage(),dto.getSize(),orderMap);
        userMapper.selectPage(page,queryWrapper);

        return OrikaUtils.convertPageVo(page,UserDto.class);
    }

    @Override
    public UserDto findUserById(Long id) {
        log.info("参数信息 id:"+ id);
        User user = userMapper.selectById(id);
        log.debug("debug 参数信息 id:"+ id);
        return OrikaUtils.convert(user,UserDto.class);
    }

}