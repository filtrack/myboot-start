package com.hjw.app;

import java.io.Serializable;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import com.sanq.product.config.utils.mvc.entity.Base;


/**
* author HJW
* description 
* date 2022-08-15
*/
@TableField("t_launage")
@Data
public class Launage implements Serializable {


    private static final long serialVersionUID = 1L;



    //创建时间
    @TableField("create_time")
    private Date createTime;


    //0:未删除 1:逻辑删除
    @TableField("delete_flag")
    private Integer deleteFlag;


    //描述
    @TableField("descs")
    private String descs;


    @TableField("id")
    private Long id;


    //名称
    @TableField("name")
    private String name;


    //最后更新时间
    @TableField("update_time")
    private Date updateTime;


    //乐观锁
    @TableField("version")
    private Integer version;

}