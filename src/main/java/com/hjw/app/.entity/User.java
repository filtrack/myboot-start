package com.hjw.app;

import java.io.Serializable;
import java.util.*;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;



/**
* author HJW
* description 
* date 2022-08-15
*/
@TableName("t_user")
@Data
public class User implements Serializable {


    private static final long serialVersionUID = 1L;



    //生日(年月日) yyyy-MM-dd
    @TableField("birthday")
    private String birthday;


    //创建时间
    @TableField("create_time")
    private Date createTime;


    //0:未删除 1:逻辑删除
    @TableField("delete_flag")
    private Integer deleteFlag;


    @TableField("id")
    private Long id;


    //签名介绍
    @TableField("intro")
    private String intro;


    //昵称
    @TableField("nickname")
    private String nickname;


    //密码
    @TableField("password")
    private String password;


    //头像
    @TableField("photo")
    private String photo;


    //阅读量
    @TableField("sex")
    private Integer sex;


    //最后更新时间
    @TableField("update_time")
    private Date updateTime;


    //登录名
    @TableField("username")
    private String username;


    //乐观锁
    @TableField("version")
    private Integer version;

}