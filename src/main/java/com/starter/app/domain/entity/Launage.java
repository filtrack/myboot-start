package com.starter.app.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
* author HJW
* description 
* date 2022-08-22
*/
@TableName("t_launage")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class Launage extends BaseEntity {

    private static final long serialVersionUID = 1L;

    //名称
    @TableField("name")
    private String name;

    //描述
    @TableField("descs")
    private String descs;


}