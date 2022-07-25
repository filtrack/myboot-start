package com.starter.app.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.starter.app.config.GlobalConst;
import com.starter.app.dto.LoginDto;
import com.starter.app.dto.MyPage;
import com.starter.app.dto.PageVo;
import com.starter.app.dto.UserDto;
import com.starter.app.entity.User;
import com.starter.app.mapper.UserMapper;
import com.starter.app.service.JWTService;
import com.starter.app.service.RedisService;
import com.starter.app.service.UserService;
import com.starter.app.utils.OrikaUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.LinkedHashMap;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    final GlobalConst globalConst;
    final UserMapper userMapper;
    final RedisService redisService;
    final JWTService jwtService;

    public UserServiceImpl(GlobalConst globalConst,
                           UserMapper userMapper,
                           RedisService redisService,
                           JWTService jwtService) {
        this.globalConst = globalConst;
        this.userMapper = userMapper;
        this.redisService = redisService;
        this.jwtService = jwtService;
    }

    @Override
    public LoginDto login(String username, String password) {
        User user = userMapper.findByUserName(username);
        Assert.notNull(user, "用户名错误");
        Assert.isTrue(SecureUtil.md5(password).endsWith(user.getPassword()), "用户密码错误");

        LoginDto loginDto = OrikaUtils.convert(user, LoginDto.class);
        String token = jwtService.createToken(loginDto.getId());
        loginDto.setToken(token);
        //缓存accountToken
        redisService.set("token:" + loginDto.getId(), loginDto, globalConst.getExpire());
        return loginDto;
    }

    @Override
    public Boolean addUser(UserDto userDto) {
        Assert.notNull(userDto, "用户信息不能为空");
        User user = OrikaUtils.convert(userDto, User.class);
        return save(user);
    }

    @Override
    public UserDto findUserById(Long id) {
        log.info("参数信息 id:"+ id);
        User user = userMapper.selectById(id);
        log.debug("debug 参数信息 id:"+ id);
        return OrikaUtils.convert(user,UserDto.class);
    }

    @Override
    public PageVo<UserDto> queryPage(UserDto dto) {
        LinkedHashMap<String,String> orderMap = new LinkedHashMap<>();
        orderMap.put("id","asc");

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(ObjectUtil.isNotEmpty(dto.getUsername()),"name",dto.getUsername());
        Page<User> page = MyPage.of(dto.getPage(),dto.getSize(),orderMap);
        userMapper.selectPage(page,queryWrapper);

        return OrikaUtils.convertPageVo(page,UserDto.class);
    }
}




