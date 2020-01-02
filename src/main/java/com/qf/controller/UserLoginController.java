package com.qf.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.qf.dto.UserDTO;
import com.qf.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * description:
 * 用户登录
 * 生成验证码
 * */
@Controller
public class UserLoginController {
    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @ResponseBody
    @RequestMapping("/sys/login")
    public R login(@RequestBody UserDTO userDTO){
        return R.ok();
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
    // 修改密码
    @RequestMapping("/sys/user/updatePassword")
    @ResponseBody
    public R updatePassword(@RequestParam String password, @RequestParam String newPassword){
        System.out.println(password);
        System.out.println(newPassword);
        return R.ok();
    }
}
