package com.starter.app.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum CodeEnum {

    CODE_SUCCESS(200,"操作成功"),
    CODE_ERROR(209,"操作失败"),
    CODE_CUSTOM(301,"自定义异常信息"),
    CODE_MISS_PARAMS(500,"请求参数缺失");

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 提示信息
     */

    private String msg;

}
