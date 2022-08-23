package com.starter.app.domain.dto;

import lombok.*;

import java.util.Date;

/**
* author HJW
* description 请求对象
* date 2022-08-22
*/
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO extends ReqPageDTO{

    private static final long serialVersionUID = 1L;

    private Long id;
    //登录名
    private String username;
    //密码
    private String password;
    //昵称
    private String nickname;
    //头像
    private String photo;
    //签名介绍
    private String intro;
    //生日(年月日) yyyy-MM-dd
    private String birthday;
    //阅读量
    private Integer sex;
    //0:未删除 1:逻辑删除
    private Integer deleteFlag;
    //乐观锁
    private Integer version;
    //创建时间
    private Date createTime;
    //最后更新时间
    private Date updateTime;

}