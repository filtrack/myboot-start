package com.starter.app.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * (person)实体类
 */
@Data
@ToString(callSuper = true)
public class UserDto extends ReqPage implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 姓名
     */
    private String name;
    /**
     * 邮箱
     */
    private String email;

}