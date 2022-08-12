package com.cool.service.impl;

import com.cool.entity.User;
import com.cool.mapper.UserMapper;
import com.cool.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hjw
 * @since 2022-08-11 15:29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
