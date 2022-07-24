package com.starter.app.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServiceException extends RuntimeException{

    private String module;
    private String code;
    private Object[] args;
    private String msg;



    // 无参构造
    public ServiceException() {}

    // 带参构造
    public ServiceException(String message) {
        // 异常错误消息
        super(message);
    }

    public ServiceException(Exception cause) {
        // 异常错误消息
        super(cause);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
        if (log.isDebugEnabled()) {
            log.debug(message);
        }
    }


}
