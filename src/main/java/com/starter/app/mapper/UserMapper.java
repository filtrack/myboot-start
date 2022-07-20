package com.starter.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.starter.app.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    User findByUserName(String username);
}
