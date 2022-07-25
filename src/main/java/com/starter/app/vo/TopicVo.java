package com.starter.app.vo;

import com.starter.app.dto.ReqPage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class TopicVo extends ReqPage {

    private Long id;

    /**
     * 语言ID
     */
    private Long lId;

    /**
     * 发布者id
     */
    private Long uId;

    /**
     * 专题名称
     */
    private String title;

    /**
     * 专题描述
     */
    private String subTitle;

}
