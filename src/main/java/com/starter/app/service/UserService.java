package com.starter.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.starter.app.domain.entity.User;
import com.starter.app.domain.vo.LoginVO;
import com.starter.app.domain.vo.PageVO;
import com.starter.app.domain.dto.UserDTO;
import com.starter.app.domain.vo.UserVO;

public interface UserService extends IService<User> {

    LoginVO login(String username, String password);

    Boolean addUser(UserDTO userDTO);

    UserVO findById(Long id);

    PageVO<UserVO> queryPage(UserDTO userDTO);
}
