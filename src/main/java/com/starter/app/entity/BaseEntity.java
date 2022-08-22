package com.starter.app.entity;


import java.io.Serializable;
import java.util.*;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
* author HJW
* description 实体基类
* date 2022-08-22
*/
@Data
public class BaseEntity implements Serializable {

    @TableField("id")
    private Long id;

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