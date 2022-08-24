package com.starter.app.domain.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.ToString;

/**
* author HJW
* description 
* date 2022-08-23
*/
@TableName("t_order")
@ToString(callSuper = true)
@Data
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("id")
    private Long id;

    @TableField("cloumn")
    private String cloumn;

    @TableField("order_id")
    private Integer orderId;

    @TableField("user_id")
    private Integer userId;

    public static void main(String[] args) {
        System.out.println(2%2);
    }


}