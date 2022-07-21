package com.starter.app.aop;

import com.starter.app.annotation.Log;
import com.starter.app.utils.HttpContextUtils;
import com.starter.app.utils.IpUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
@Slf4j
@Component
public class LogAspect {

    @Pointcut("@annotation(com.starter.app.annotation.Log)")
    public void pointcut() { }



    @SneakyThrows
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        log.info("========================================== Start ==========================================");

        MethodSignature signature = (MethodSignature) point.getSignature();

        // 请求的方法名
        Method method = signature.getMethod();
        String reqUrl = request.getRequestURL().toString();
        String className = point.getTarget().getClass().getName();
        String methodName = method.getName();
        String title = "Method-Desc";
        Log logAnnotation = method.getAnnotation(Log.class);
        if (logAnnotation != null) {
            title = logAnnotation.value();
        }
        // 请求的方法参数值
        Object[] args = point.getArgs();
        // 请求的方法参数名称
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paramNames = u.getParameterNames(method);
        String params = "";
        if (args != null && paramNames != null) {
            for (int i = 0; i < args.length; i++) {
                params += paramNames[i] + ":" + args[i]+" ";
            }
        }
        String ip = IpUtils.getIpAddr(request);
        log.info("[{}]请求ip:{},请求地址:{}",title,ip,reqUrl);
        log.info("类和方法:{}#{}",className,methodName);
        log.info("参数信息:{}",params);

        Object result = null;
        long beginTime = System.currentTimeMillis();
        try {
            // 执行方法
            result = point.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        Object res = point.proceed();
        log.info("耗时:{}毫秒,响应结果:{}",time,res.toString());
        log.info("==========================================  End  ==========================================\n");
        return result;
    }

}
