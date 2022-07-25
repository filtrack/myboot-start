package com.starter.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseEntity implements Serializable {
    @TableId
    private Long id;

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
}
