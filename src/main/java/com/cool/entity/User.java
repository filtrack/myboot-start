package com.cool.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author hjw
 * @since 2022-08-11 15:29
 */
@Getter
@Setter
@TableName("t_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 登录名
     */
    @TableField("username")
    private String username;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 昵称
     */
    @TableField("nickname")
    private String nickname;

    /**
     * 头像
     */
    @TableField("photo")
    private String photo;

    /**
     * 签名介绍
     */
    @TableField("intro")
    private String intro;

    /**
     * 生日(年月日) yyyy-MM-dd
     */
    @TableField("birthday")
    private String birthday;

    /**
     * 阅读量
     */
    @TableField("sex")
    private Integer sex;

    /**
     * 0:未删除 1:逻辑删除
     */
    @TableField("delete_flag")
    private Integer deleteFlag;

    /**
     * 乐观锁
     */
    @TableField("version")
    private Integer version;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 最后更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;


}
