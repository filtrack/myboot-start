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
public class LaunageDTO extends ReqPageDTO{

    private static final long serialVersionUID = 1L;

    private Long id;
    //名称
    private String name;
    //描述
    private String descs;
    //0:未删除 1:逻辑删除
    private Integer deleteFlag;
    //乐观锁
    private Integer version;
    //创建时间
    private Date createTime;
    //最后更新时间
    private Date updateTime;

}