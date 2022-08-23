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
public class AricleVO extends ReqPageDTO {

    private static final long serialVersionUID = 1L;

    private Long id;

    //语言id
//    private Long lId;
    private String launage;

    //专题id
//    private Long tId;
    private String topic;

    //发布者id
//    private Long uId;
    private String username;

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

    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;


}