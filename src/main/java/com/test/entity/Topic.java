package com.test;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.util.Date;

/**
 * 专题
 * @author hjw
 * @date 2022/08/11 14:38
 */
@Data
@TableName("t_topic")
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
