package com.qf.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 自定义日志注解
 * @author zhangqi
 * 创建时间：2020/2/13
 * */
// 注解用于方法
@Target(ElementType.METHOD)
// 注解的生命周期阶段为运行阶段
@Retention(RetentionPolicy.RUNTIME)
public @interface MyLog {
    String value() default "用户操作";
}

