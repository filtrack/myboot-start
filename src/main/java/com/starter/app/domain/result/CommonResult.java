package com.starter.app.domain.result;

import com.starter.app.exception.CodeEnum;
import lombok.Data;

import java.io.Serializable;

@Data
public class CommonResult<T> implements Serializable {

    /**
     * 返回码
     */
    private Integer code;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 返回对象
     */
    private T data;

    /**
     * 时间戳
     */
    private Long time ;


    public static <T> CommonResult<T> success() {
        return createResult(CodeEnum.CODE_SUCCESS.getCode(), null, null);
    }

    public static <T> CommonResult<T> success(T data) {
        return createResult(CodeEnum.CODE_SUCCESS.getCode(), CodeEnum.CODE_SUCCESS.getMsg(), data);
    }

    public static <T> CommonResult<T> success(String message) {
        return createResult(CodeEnum.CODE_SUCCESS.getCode(), message, null);
    }

    public static <T> CommonResult<T> success(T data, String message) {
        return createResult(CodeEnum.CODE_SUCCESS.getCode(), message, data);
    }

    public static <T> CommonResult<T> error() {
        return createResult(CodeEnum.CODE_ERROR.getCode(), CodeEnum.CODE_ERROR.getMsg(), null);
    }

    public static <T> CommonResult<T> error(CodeEnum codeEnum) {
        return createResult(codeEnum.getCode(), codeEnum.getMsg(), null);
    }

    public static <T> CommonResult<T> error(T t) {
        return createResult(CodeEnum.CODE_ERROR.getCode(), CodeEnum.CODE_ERROR.getMsg(),t);
    }

    public static <T> CommonResult<T> error(CodeEnum codeEnum, T data) {
        return createResult(codeEnum.getCode(), codeEnum.getMsg(), data);
    }

    public static <T> CommonResult<T> error(CodeEnum codeEnum, String message) {
        return createResult(codeEnum.getCode(),
                String.format("%s %s", codeEnum.getMsg(), message), null);
    }

    public static <T> CommonResult<T> error(Integer code, String message) {
        return createResult(code, message, null);
    }

    private static <T> CommonResult<T> createResult(Integer code, String message, T data) {
        CommonResult<T> r = new CommonResult<>();
        r.setCode(code);
        r.setMessage(message);
        r.setData(data);
        r.setTime(System.currentTimeMillis());
        return r;
    }


}
