package com.starter.app.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;


@TableName(value ="t_topic")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class Topic extends BaseEntity {


    /**
     * 语言ID
     */
    private String lId;

    /**
     * 发布者id
     */
    private String uId;

    /**
     * 专题名称
     */
    private String title;

    /**
     * 专题描述
     */
    private String subTitle;

}