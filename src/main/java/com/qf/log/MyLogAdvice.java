package com.qf.log;

import com.alibaba.fastjson.JSON;
import com.qf.util.HttpContextUtils;
import com.qf.util.IPUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * aop实现日志记录
 * @author zhangqi
 * 创建时间：2020/2/13
 * */
@Aspect
@Component
public class MyLogAdvice {
    private Logger logger = Logger.getLogger(MyLogAdvice.class);

    /**
     * 定义切点，切点为自定义的注解@MyLog
     * */
    @Pointcut("@annotation(com.qf.log.MyLog)")
    public void myPointCut() {
    }

    /**
     * 定义后置通知
     **/
    @AfterReturning(pointcut = "myPointCut()")
    public void myAfterAdvice(JoinPoint joinPoint) {
        // 获取方法签名
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        // 获取方法名称
        Method method = signature.getMethod();
        // 获取@MyLog注解
        MyLog annotation = method.getAnnotation(MyLog.class);
        String operation = null;
        if (annotation != null) {
            // 获取用户的操作
            operation = annotation.value();
        }
        // 获取用户ip
        String ipAddr = IPUtils.getIpAddr(HttpContextUtils.getHttpServletRequest());
        // 获取参数
        Object[] args = joinPoint.getArgs();
        String jsonString = JSON.toJSONString(args);
        String methodName = joinPoint.getTarget().getClass().getName() + "." + method.getName();
        logger.info(new Date() + "|" + operation + "|" + ipAddr + "|" + jsonString + "|" + methodName);
    }
}
