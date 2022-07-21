package com.starter.app.service;


import com.starter.app.dto.PageVo;
import com.starter.app.dto.UserDto;
import com.starter.app.entity.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

/**
 * 服务接口
 */
@CacheConfig(cacheNames = "user")
public interface UserService {

    boolean add(User user);

    PageVo<UserDto> queryPage(UserDto userDto);

    @Cacheable(key = "#p0")
    UserDto findUserById(Long id);

    User findByUserName(String username);
}
