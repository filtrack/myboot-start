package com.starter.app.domain.dto;

import lombok.*;

/**
* author HJW
* description 请求对象
* date 2022-08-23
*/
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDTO extends ReqPageDTO{

    private static final long serialVersionUID = 1L;

    private String cloumn;
    private Integer id;
    private Integer orderId;
    private Integer userId;

}