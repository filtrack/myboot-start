package com.cool.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author hjw
 * @since 2022-08-11 15:29
 */
@Getter
@Setter
@TableName("t_aricle")
public class Aricle implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 语言id
     */
    @TableField("l_id")
    private Long lId;

    /**
     * 专题id
     */
    @TableField("t_id")
    private Long tId;

    /**
     * 发布者id
     */
    @TableField("u_id")
    private Long uId;

    /**
     * 文章标题
     */
    @TableField("title")
    private String title;

    /**
     * 文章副标题
     */
    @TableField("sub_title")
    private String subTitle;

    /**
     * 文章标题
     */
    @TableField("content")
    private String content;

    /**
     * 关键字
     */
    @TableField("keyword")
    private String keyword;

    /**
     * 阅读量
     */
    @TableField("read_count")
    private Integer readCount;

    /**
     * 0:暂存 1:发布
     */
    @TableField("status")
    private Integer status;

    /**
     * 0:未删除 1:逻辑删除
     */
    @TableField("delete_flag")
    private Integer deleteFlag;

    /**
     * 乐观锁
     */
    @TableField("version")
    private Integer version;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 最后更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;


}
