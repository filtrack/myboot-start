package com.starter.app.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.starter.app.domain.dto.ReqPageDTO;
import lombok.*;

import java.util.Date;

/**
* author HJW
* description 响应对象
* date 2022-08-22
*/
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserVO extends ReqPageDTO {

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

    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;




}