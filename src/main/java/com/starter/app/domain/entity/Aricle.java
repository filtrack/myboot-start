package com.starter.app.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@TableName(value ="t_aricle")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class Aricle extends BaseEntity {


    /**
     * 语言id
     */
    private Long lId;

    /**
     * 专题id
     */
    private Long tId;

    /**
     * 发布者id
     */
    private Long uId;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章副标题
     */
    private String subTitle;

    /**
     * 文章标题
     */
    private String content;

    /**
     * 关键字
     */
    private String keyword;

    /**
     * 阅读量
     */
    private Integer readCount;


    /**
     * 0:暂存 1:发布
     */
    private Integer status;
}