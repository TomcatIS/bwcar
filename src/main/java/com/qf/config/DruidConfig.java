package com.qf.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 实现sql监控的配置类
 * @author zhangqi
 * 创建时间：2020/2/13
 * */
@Configuration
public class DruidConfig {

    @Bean(name = "dataSource")
    // 加载properties文件以spring.datasource开头的部分构建数据源
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource(){
       DruidDataSource druidDataSource = new DruidDataSource();
       return druidDataSource;
   }
}
