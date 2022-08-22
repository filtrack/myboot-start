package com.starter.app.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
* author HJW
* description 专题
* date 2022-08-22
*/
@TableName("t_topic")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class Topic extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableField("l_id")
    private Long lId;

    //发布者id
    @TableField("u_id")
    private Long uId;

    //专题名称
    @TableField("title")
    private String title;

    //专题描述
    @TableField("sub_title")
    private String subTitle;


}