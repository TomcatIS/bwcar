package com.qf.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.qf.dto.UserDTO;
import com.qf.util.R;
import com.qf.util.ShiroUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
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
        String customKaptcha = userDTO.getCaptcha();
        String serverKaptcha = ShiroUtils.getKaptcha();
        if (!customKaptcha.equalsIgnoreCase(serverKaptcha)) {
            return R.error("验证码错误");
        }
        String password = userDTO.getPassword();
        // 用户名作为盐值
        String username = userDTO.getUsername();
        Md5Hash md5Hash = new Md5Hash(password, username, 1024);
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, md5Hash.toString());
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(usernamePasswordToken);
            if (userDTO.getRememberMe()){
                usernamePasswordToken.setRememberMe(true);
            }
        } catch (Exception e) {
            System.out.println("用户名或密码错误");
            return R.error("用户名或密码错误");
        }
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
        // 将验证码放进shiro的session中做缓存
        ShiroUtils.setKaptcha(kaptchaText);
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
        return R.ok();
    }
}
