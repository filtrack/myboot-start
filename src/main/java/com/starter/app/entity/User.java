package com.starter.app.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@TableName(value ="t_user")
@Data
public class User implements Serializable {
    /**
     * 
     */
    @TableId
    private String id;

    /**
     * 登录名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String photo;

    /**
     * 签名介绍
     */
    private String intro;

    /**
     * 生日(年月日) yyyy-MM-dd
     */
    private String birthday;

    /**
     * 阅读量
     */
    private Integer sex;

    /**
     * 0:未删除 1:逻辑删除
     */
    private Integer deleteFlag;

    /**
     * 乐观锁
     */
    private Integer version;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后更新时间
     */
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}