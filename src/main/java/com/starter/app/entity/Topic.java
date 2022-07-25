package com.starter.app.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@TableName(value ="t_topic")
@Data
public class Topic implements Serializable {
    /**
     * 
     */
    @TableId
    private String id;

    /**
     * 
     */
    private String lId;

    /**
     * 发布者id
     */
    private String uId;

    /**
     * 专题名称
     */
    private String name;

    /**
     * 专题描述
     */
    private String desc;

    /**
     * 0:未删除 1:逻辑删除
     */
    private Integer deleteFlag;

    /**
     * 乐观锁
     */
    private Integer version;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后更新时间
     */
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}