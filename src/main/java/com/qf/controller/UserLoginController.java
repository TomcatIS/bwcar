package com.qf.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * description:
 * 生成验证码
 * */
@Controller
public class UserLoginController {
    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @RequestMapping("/sys/login")
    public ModelAndView login(ModelAndView modelAndView){
        modelAndView.setViewName("login");
        return modelAndView;
    }

    /**
     * description:
     * 生成验证码
     * */
    @RequestMapping("/captcha.jpg")
    public void generateCaptcha(HttpServletResponse response){
        // 设置响应的验证码不缓存
        response.setHeader("Cache-Control", "no-cache, no-store");
        response.setContentType("image/jpg");
        // 生成验证码文本
        String kaptchaText = defaultKaptcha.createText();
        // 生成验证码图片
        BufferedImage kaptchaImage = defaultKaptcha.createImage(kaptchaText);
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            ImageIO.write(kaptchaImage, "jpg", outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
