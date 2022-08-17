package com.hjw.app.service.impl;

import com.hjw.app.service.UserService;
import com.hjw.app.mapper.UserMapper;
import org.springframework.stereotype.Service;


/**
* author HJW
* description 
* date 2022-08-16
*/
@Service
public class UserServiceImpl implements UserService {

    final UserMapper userMapper;
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}

