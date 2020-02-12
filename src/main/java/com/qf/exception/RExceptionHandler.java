package com.qf.exception;

import com.qf.util.R;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常处理器
 * 创建时间：2020/2/12
 * */
@ControllerAdvice
public class RExceptionHandler {
    @ExceptionHandler(RException.class)
    @ResponseBody
    public R rExp(RException rException){
        R r = new R();
        r.put("code",500);
        r.put("msg",rException.getMsg());
        return r;
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R allExp(Exception exception){
        R r = new R();
        r.put("code",500);
        r.put("msg","系统内部异常，请联系管理员");
        return r;
    }

    @ExceptionHandler(AuthorizationException.class)
    public String authorExce(AuthorizationException authorizationException){
        return "redirect:unauthorized.html";
    }
}
