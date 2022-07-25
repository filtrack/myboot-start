package com.starter.app.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@TableName(value ="t_launage")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class Launage extends BaseEntity {

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String descs;

}