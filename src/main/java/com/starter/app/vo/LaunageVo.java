package com.starter.app.vo;

import com.starter.app.dto.ReqPage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class LaunageVo extends ReqPage {

    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String descs;

}
