package com.starter.app.service;


import com.starter.app.dto.PageVo;
import com.starter.app.dto.UserDto;
import com.starter.app.entity.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

/**
 * 服务接口
 */
public interface UserService {

    boolean add(User user);

    PageVo<UserDto> queryPage(UserDto userDto);

    UserDto findUserById(Long id);

    User findByUserName(String username);
}
