package com.hjw.app.entity;

import java.io.Serializable;
import java.util.*;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
* author HJW
* description 
* date 2022-08-16
*/
@TableName("t_user")
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;


    @TableField("id")
    private Long id;

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

    //0:未删除 1:逻辑删除
    @TableField("delete_flag")
    private Integer deleteFlag;

    //乐观锁
    @TableField("version")
    private Integer version;

    //创建时间
    @TableField("create_time")
    private Date createTime;

    //最后更新时间
    @TableField("update_time")
    private Date updateTime;

}