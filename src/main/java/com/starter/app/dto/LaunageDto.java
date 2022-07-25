package com.starter.app.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@NoArgsConstructor
public class LaunageDto extends ReqPage{

    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String descs;



    public LaunageDto(String name, String descs) {
        this.name = name;
        this.descs = descs;
    }

}
