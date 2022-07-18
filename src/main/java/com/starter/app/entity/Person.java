package com.starter.app.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * (person)实体类
 */
@Data
@NoArgsConstructor
public class Person extends Model<Person> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 编号，自动增长
     */
    @TableId
	private Integer id;
    /**
     * 名称
     */
    private String name;
    /**
     * 性别 0-男 1-女
     */
    private Integer sex;
    /**
     * 是否删除 0-未删除 1-已删除
     */
    private Integer isDeleted;

    public Person(String name, int sex) {
        this.name = name;
        this.sex = sex;
    }
}