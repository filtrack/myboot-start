package com.starter.app.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.starter.app.domain.dto.UserDTO;
import com.starter.app.domain.entity.User;
import com.starter.app.mapper.UserMapper;
import com.starter.app.service.UserService;
import com.starter.app.config.GlobalConst;
import com.starter.app.domain.ext.MyPage;
import com.starter.app.service.JWTService;
import com.starter.app.service.RedisService;
import com.starter.app.utils.OrikaUtils;
import com.starter.app.domain.vo.LoginVO;
import com.starter.app.domain.vo.PageVO;
import com.starter.app.domain.vo.UserVO;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.LinkedHashMap;

/**
* author HJW
* description 
* date 2022-08-22
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>  implements UserService {

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
    public LoginVO login(String username, String password) {
        User user = userMapper.findByUserName(username);
        Assert.notNull(user, "用户名错误");
        Assert.isTrue(SecureUtil.md5(password).endsWith(user.getPassword()), "用户密码错误");

        LoginVO loginVo = OrikaUtils.convert(user, LoginVO.class);
        String token = jwtService.createToken(loginVo.getId().toString());
        loginVo.setToken(token);
        //缓存accountToken
        redisService.set("token:" + loginVo.getId(), loginVo, globalConst.getExpire());

        return loginVo;
    }

    @Override
    public Boolean addUser(UserDTO dto) {
        Assert.notNull(dto, "用户信息不能为空");
        User user = OrikaUtils.convert(dto, User.class);
        return save(user);
    }

    @Override
    public UserVO findById(Long id) {
        Assert.notNull(id, "用户id不能为空");
        User user = userMapper.selectById(id);
        Assert.notNull(user, "用户信息不存在");
        return OrikaUtils.convert(user, UserVO.class);
    }

    @Override
    public PageVO<UserVO> queryPage(UserDTO dto) {
        LinkedHashMap<String,String> orderMap = new LinkedHashMap<>();
        orderMap.put("id","asc");

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(ObjectUtil.isNotEmpty(dto.getUsername()),"username",dto.getUsername());
        Page<User> page = MyPage.of(dto.getPage(),dto.getSize(),orderMap);
        userMapper.selectPage(page,queryWrapper);

        return OrikaUtils.convertPageVo(page, UserVO.class);
    }

}

