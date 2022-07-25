package com.starter.app.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class TopicDto extends ReqPage{

    private Long id;

    /**
     * 语言ID
     */
    private String lId;

    /**
     * 发布者id
     */
    private String uId;

    /**
     * 专题名称
     */
    private String title;

    /**
     * 专题描述
     */
    private String subTitle;

    public TopicDto(String lid, String uid, String title, String subTitle) {
        this.lId = lid;
        this.uId = uid;
        this.title = title;
        this.subTitle = subTitle;
    }
}
