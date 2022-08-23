package com.starter.app.aspectj.aop;

import com.starter.app.aspectj.annotation.Log;
import com.starter.app.utils.IpUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;


@Slf4j
@Component
@Aspect
public class LogAccessAspect {

    @Pointcut("@annotation(com.starter.app.aspectj.annotation.Log)")
    public void pointcut() { }

    @SneakyThrows
    @Before(value = "pointcut()")
    public void before(JoinPoint point) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        Assert.notNull(attributes,"attributes is null");

        HttpServletRequest request = attributes.getRequest();
        MethodSignature signature = (MethodSignature) point.getSignature();
        // 请求的方法名
        Method method = signature.getMethod();
        String reqUrl = request.getRequestURL().toString();
        String title = "Method-Desc";
        Log logAnnotation = method.getAnnotation(Log.class);
        if (logAnnotation != null) {
            title = logAnnotation.value();
        }
        String ip = IpUtils.getIp(request);
        //执行方法
        // 执行时长(毫秒)
        log.info("[{}] ip:{},请求地址[{}]",title,ip,reqUrl);
    }

}
