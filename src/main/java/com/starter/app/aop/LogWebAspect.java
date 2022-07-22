package com.starter.app.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Slf4j
@Component
public class LogWebAspect {


    @Pointcut("@annotation(com.starter.app.annotation.Log)")
    public void pointcut() {
    }


    @Around(value = "pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        MethodSignature signature = (MethodSignature) point.getSignature();
        // 请求的方法名
        Method method = signature.getMethod();
        String className = point.getTarget().getClass().getName();
        String methodName = method.getName();
        // 请求的方法参数值
        Object[] args = point.getArgs();
        // 请求的方法参数名称
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paramNames = u.getParameterNames(method);
        StringBuilder params = new StringBuilder("");
        if (args != null && paramNames != null) {
            for (int i = 0; i < args.length; i++) {
                params.append(paramNames[i]).append(":").append(args[i]).append(" ");
            }
        }
        log.info("请求类和方法:{}#{}", className, methodName);
        log.info("请求参数信息:{}", params);
        // 执行方法
        Object result;
        try {
            result = point.proceed();
        } catch (Throwable e) {
            log.info("方法执行异常:{}", e.getMessage());
            throw e;
        }
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        log.info("请求耗时:{}毫秒,响应结果:{}", time, result);
        return result;
    }

}
