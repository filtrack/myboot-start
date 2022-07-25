package com.starter.app.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;


@TableName(value ="t_user")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class User extends BaseEntity {

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

}