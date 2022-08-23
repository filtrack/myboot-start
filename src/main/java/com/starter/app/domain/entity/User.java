package com.starter.app.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
* author HJW
* description 
* date 2022-08-22
*/
@TableName("t_user")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    //登录名
    @TableField("username")
    private String username;

    //密码
    @TableField("password")
    private String password;

    //昵称
    @TableField("nickname")
    private String nickname;

    //头像
    @TableField("photo")
    private String photo;

    //签名介绍
    @TableField("intro")
    private String intro;

    //生日(年月日) yyyy-MM-dd
    @TableField("birthday")
    private String birthday;

    //阅读量
    @TableField("sex")
    private Integer sex;


}