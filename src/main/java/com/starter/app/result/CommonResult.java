package com.starter.app.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

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
        return createResult(ResponseCode.SUCCESS.getCode(), null, null);
    }

    public static <T> CommonResult<T> success(T data) {
        return createResult(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), data);
    }

    public static <T> CommonResult<T> success(String message) {
        return createResult(ResponseCode.SUCCESS.getCode(), message, null);
    }

    public static <T> CommonResult<T> success(T data, String message) {
        return createResult(ResponseCode.SUCCESS.getCode(), message, data);
    }

    public static <T> CommonResult<T> error() {
        return createResult(ResponseCode.ERROR_SYS.getCode(), ResponseCode.ERROR_SYS.getMessage(), null);
    }

    public static <T> CommonResult<T> error(ResponseCode responseCode) {
        return createResult(responseCode.getCode(), responseCode.getMessage(), null);
    }

    public static <T> CommonResult<T> error(T t) {
        return createResult(ResponseCode.ERROR_SYS.getCode(), ResponseCode.ERROR_SYS.getMessage(),t);
    }

    public static <T> CommonResult<T> error(ResponseCode responseCode, T data) {
        return createResult(responseCode.getCode(), responseCode.getMessage(), data);
    }

    public static <T> CommonResult<T> error(ResponseCode responseCode, String message) {
        return createResult(responseCode.getCode(),
                String.format("%s %s", responseCode.getMessage(), message), null);
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


    @Getter
    @AllArgsConstructor
    public enum  ResponseCode{
        /**操作成功**/
        SUCCESS(200,"操作成功"),
        /**操作失败**/
        ERROR_SYS(300,"操作失败"),
        /**请求参数缺失**/
        ERROR_PARAMS(500,"请求参数缺失"),
        /**请求参数缺失**/
        ERROR_CUS(600,"通用自定义设置错误描述");


        private final int code;
        private final String message;

    }
}
