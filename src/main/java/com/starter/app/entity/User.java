package com.starter.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (person)实体类
 */
@Data
@NoArgsConstructor
public class User extends Model<User> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 编号，自动增长
     */
    @TableId
	private Integer id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 邮箱
     */
    private String email;

    public User(String name, int age,String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }
}