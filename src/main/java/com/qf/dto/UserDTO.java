package com.qf.dto;
/**
 * description:
 * 后端传递到login.html的数据
 * @author zhangqi
 * 2019/12/28
 * */
public class UserDTO {
    private String username;
    private String password;
    private String captcha;
    private String rememberMe;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(String rememberMe) {
        this.rememberMe = rememberMe;
    }
}
