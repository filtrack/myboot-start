package com.starter.app.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@TableName(value ="t_aricle")
@Data
public class Aricle implements Serializable {
    /**
     * 
     */
    @TableId
    private String id;

    /**
     * 语言id
     */
    private String lId;

    /**
     * 专题id
     */
    private String tId;

    /**
     * 发布者id
     */
    private String uId;

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