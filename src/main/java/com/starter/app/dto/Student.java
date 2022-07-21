package com.starter.app.dto;

import com.starter.app.validation.ValidGroup;
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
    @Null(groups = ValidGroup.Create.class)
    @NotBlank(groups = ValidGroup.Update.class,message = "ID不能为空")
    private String id;

    /**
     * 学生姓名
     */
    @NotBlank(groups = ValidGroup.Update.class,message = "应用ID不能为空")
    @Length(min = 12,max = 24)
    private String name;

    /**
     * 学生邮箱
     */
    @NotBlank(groups = ValidGroup.Update.class,message = "应用ID不能为空")
    private String email;
}