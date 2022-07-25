package com.starter.app.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.starter.app.dto.ReqPage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class UserVo extends ReqPage{

    private Long id;

    /**
     * 登录名
     */
    private String username;

    /**
     * 密码
     */
    @JsonIgnore
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