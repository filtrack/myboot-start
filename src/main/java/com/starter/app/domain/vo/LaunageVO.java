package com.starter.app.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.starter.app.domain.dto.ReqPageDTO;
import lombok.*;

import java.util.Date;

/**
* author HJW
* description 响应对象
* date 2022-08-22
*/
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LaunageVO extends ReqPageDTO {

    private static final long serialVersionUID = 1L;

    private Long id;

    //名称
    private String name;

    //描述
    private String descs;

    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;


}