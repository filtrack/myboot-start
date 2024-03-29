package com.starter.app.aspectj.aop;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.starter.app.exception.CodeEnum;
import com.starter.app.domain.result.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.rmi.ServerException;
import java.util.stream.Collectors;


@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {
    /**
     * 默认全局异常处理。
     * @param e the e
     * @return ResultData
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResult<String> exception(Exception e) {
        log.error("全局异常信息:",e);
        return CommonResult.error(CodeEnum.CODE_ERROR.getCode(),e.getMessage());
    }

    @ExceptionHandler(value = { IllegalArgumentException.class,IllegalStateException.class})
    public CommonResult<String> handleHttpMessageNotReadableException(Exception e) {
        log.error("异常信息:",e);
        return CommonResult.error(CodeEnum.CODE_CUSTOM.getCode(),e.getMessage());
    }

    @ExceptionHandler(value = {JWTDecodeException.class, SignatureVerificationException.class})
    public CommonResult<String> handleJwtException(Exception e) {
        log.error("jwt异常信息:",e);
        if(e instanceof JWTDecodeException){
            return CommonResult.error(CodeEnum.CODE_CUSTOM.getCode(),"JWT解码异常");
        }else if(e instanceof SignatureVerificationException){
            return CommonResult.error(CodeEnum.CODE_CUSTOM.getCode(),"签名验证未通过");
        }
        return CommonResult.error(CodeEnum.CODE_CUSTOM.getCode(),e.getMessage());
    }

    @ExceptionHandler(value = {BindException.class, ValidationException.class, MethodArgumentNotValidException.class})
    public CommonResult<String> handleValidatedException(Exception e) {
        log.error("参数信息异常:",e);
        if (e instanceof MethodArgumentNotValidException) {
            // BeanValidation exception
            MethodArgumentNotValidException ex = (MethodArgumentNotValidException) e;
            return CommonResult.error(CodeEnum.CODE_MISS_PARAMS.getCode(),
                    ex.getBindingResult().getAllErrors().stream()
                            .map(ObjectError::getDefaultMessage)
                            .collect(Collectors.joining("; "))
            );
        } else if (e instanceof ConstraintViolationException) {
            // BeanValidation GET simple param
            ConstraintViolationException ex = (ConstraintViolationException) e;
            return CommonResult.error(CodeEnum.CODE_MISS_PARAMS.getCode(),
                    ex.getConstraintViolations().stream()
                            .map(ConstraintViolation::getMessage)
                            .collect(Collectors.joining("; "))
            );
        } else if (e instanceof BindException) {
            // BeanValidation GET object param
            BindException ex = (BindException) e;
            return CommonResult.error(CodeEnum.CODE_MISS_PARAMS.getCode(),
                    ex.getAllErrors().stream()
                            .map(ObjectError::getDefaultMessage)
                            .collect(Collectors.joining("; "))
            );
        }else{
            return CommonResult.error(CodeEnum.CODE_ERROR.getCode(),e.getMessage());
        }
    }

    @ExceptionHandler(value = { HttpMessageNotReadableException.class})
    public CommonResult<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return CommonResult.error(CodeEnum.CODE_CUSTOM.getCode(),"参数消息读取异常，请检查参数信息、请求方法、跨域或序列化问题 ");
    }

    @ExceptionHandler(value = { ServerException.class})
    public CommonResult<String> handleServerException(ServerException e) {
        log.error("异常信息:",e.getMessage());
        return CommonResult.error(CodeEnum.CODE_CUSTOM.getCode(),e.getMessage());
    }

}