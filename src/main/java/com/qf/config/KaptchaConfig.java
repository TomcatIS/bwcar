package com.qf.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * description:
 * 生成google验证码
 * @author zhangqi
 * 2019/12/28
 * */
@Configuration
public class KaptchaConfig {
    @Bean
    public DefaultKaptcha generateCaptcha(){
        Properties properties = new Properties();
        properties.setProperty("kaptcha.border", "yes");
        properties.setProperty("kaptcha.border.color", "black");
        // 验证码长度
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        // 验证码字体
        properties.setProperty("kaptcha.textproducer.font.names", "Courier");
        // com.google.code.kaptcha.util.Config
        Config config = new Config(properties);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}
