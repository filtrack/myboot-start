package com.starter.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.starter.app.dto.LoginDto;
import com.starter.app.dto.PageVo;
import com.starter.app.dto.UserDto;
import com.starter.app.entity.User;

public interface UserService extends IService<User> {

    LoginDto login(String username, String password);

    Boolean addUser(UserDto userDto);

    UserDto findUserById(Long id);

    PageVo<UserDto> queryPage(UserDto userDto);
}
