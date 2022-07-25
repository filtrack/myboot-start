package com.starter.app.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@NoArgsConstructor
public class AricleDto extends ReqPage{

    private Long id;

    /**
     * 语言id
     */
    private Long lId;

    /**
     * 专题id
     */
    private Long tId;

    /**
     * 发布者id
     */
    private Long uId;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章副标题
     */
    private String subTitle;

    /**
     * 文章标题
     */
    private String content;

    /**
     * 关键字
     */
    private String keyword;

    /**
     * 阅读量
     */
    private Integer readCount;


    /**
     * 0:暂存 1:发布
     */
    private Integer status;



    public AricleDto(Long lId, Long tId, Long uId, String title, String subTitle, String content, String keyword) {
        this.lId = lId;
        this.tId = tId;
        this.uId = uId;
        this.title = title;
        this.subTitle = subTitle;
        this.content = content;
        this.keyword = keyword;
    }
}
