package com.qf.config;

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * sql监控过滤器
 * @author zhangqi
 *  创建时间：2020/2/13
 * */
@WebFilter(filterName = "druidWebStatFilter", urlPatterns = "/*",
initParams = {@WebInitParam(name = "exclusions", value = "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")})
public class DruidWebStatFilter extends WebStatFilter {
}

