package com.starter.app.service.impl;

import com.starter.app.entity.SysPermission;
import com.starter.app.entity.User;
import com.starter.app.service.SysPermissionService;
import com.starter.app.service.UserDetailsService;
import com.starter.app.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserService userService;

    @Autowired
    SysPermissionService permissionService;

    @Override
    public UserDetails findByUsername(String username) throws UsernameNotFoundException {
        Assert.isNull(username,"用户名不能为空");
        User user = userService.findByUserName(username);

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        //获取该用户所拥有的权限
        List<SysPermission> sysPermissions = permissionService.selectListByUser(user.getId());
        // 声明用户授权
        sysPermissions.forEach(sysPermission -> {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(sysPermission.getPermissionCode());
            grantedAuthorities.add(grantedAuthority);
        });String username, String password, Collection<? extends GrantedAuthority> authorities
        org.springframework.security.core.userdetails.User ssu=new org.springframework.security.core.userdetails.User(user.getName(),user.get(),grantedAuthorities);
        ssu.setSysuser(user);
        return ssu;
    }
}
