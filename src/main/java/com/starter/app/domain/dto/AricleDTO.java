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
public class AricleDTO extends ReqPageDTO{

    private static final long serialVersionUID = 1L;

    private Long id;
    //语言id
    private Long lId;
    //专题id
    private Long tId;
    //发布者id
    private Long uId;
    //文章标题
    private String title;
    //文章副标题
    private String subTitle;
    //文章标题
    private String content;
    //关键字
    private String keyword;
    //阅读量
    private Integer readCount;
    //0:暂存 1:发布
    private Integer status;
    //0:未删除 1:逻辑删除
    private Integer deleteFlag;
    //乐观锁
    private Integer version;
    //创建时间
    private Date createTime;
    //最后更新时间
    private Date updateTime;

}