package com.starter.app.service;


import com.starter.app.dto.PageVo;
import com.starter.app.dto.UserDto;
import com.starter.app.entity.User;

/**
 * 服务接口
 */
public interface UserService {

    void add(User user);

    PageVo<UserDto> queryPage(UserDto userDto);

    UserDto findUserById(Long id);
}
