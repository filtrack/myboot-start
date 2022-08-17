package com.hjw.app.entity;

import java.io.Serializable;
import java.util.*;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
* author HJW
* description 
* date 2022-08-16
*/
@TableName("t_launage")
@Data
public class Launage implements Serializable {

    private static final long serialVersionUID = 1L;


    @TableField("id")
    private Long id;

    //名称
    @TableField("name")
    private String name;

    //描述
    @TableField("descs")
    private String descs;

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