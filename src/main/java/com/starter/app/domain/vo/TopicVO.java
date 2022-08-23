package com.starter.app.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.starter.app.domain.dto.ReqPageDTO;
import lombok.*;
import java.util.Date;

/**
* author HJW
* description 专题响应对象
* date 2022-08-22
*/
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TopicVO extends ReqPageDTO {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long lId;

    //发布者id
    private Long uId;

    //专题名称
    private String title;

    //专题描述
    private String subTitle;

    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;


}