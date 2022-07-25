package com.starter.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.starter.app.vo.LoginVo;
import com.starter.app.vo.PageVo;
import com.starter.app.dto.UserDto;
import com.starter.app.vo.UserVo;

public interface UserService extends IService<com.starter.app.entity.User> {

    LoginVo login(String username, String password);

    Boolean addUser(UserDto userDto);

    UserVo findById(Long id);

    PageVo<UserVo> queryPage(UserDto userDto);
}
