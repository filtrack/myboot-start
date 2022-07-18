package com.starter.app.dto;

import com.starter.app.validation.Crud;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;


/**
 * 学生对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    /**
     * 学生id
     */
    @Null(groups = Crud.Create.class)
    @NotBlank(groups = Crud.Update.class,message = "ID不能为空")
    private String id;

    /**
     * 学生姓名
     */
    @NotBlank(groups = Crud.Update.class,message = "应用ID不能为空")
    @Length(min = 12,max = 24)
    private String name;

    /**
     * 学生邮箱
     */
    @NotBlank(groups = Crud.Update.class,message = "应用ID不能为空")
    private String email;
}