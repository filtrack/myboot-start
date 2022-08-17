package com.hjw.app.entity;

import java.io.Serializable;
import java.util.*;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
* author HJW
* description 专题
* date 2022-08-16
*/
@TableName("t_topic")
@Data
public class Topic implements Serializable {

    private static final long serialVersionUID = 1L;


    @TableField("id")
    private Long id;

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

    //0:未删除 1:逻辑删除
    @TableField("delete_flag")
    private Integer deleteFlag;

    //乐观锁
    @TableField("version")
    private Integer version;

    //创建时间
    @TableField("create_time")
    private Date createTime;

    //最后更新时间
    @TableField("update_time")
    private Date updateTime;

}