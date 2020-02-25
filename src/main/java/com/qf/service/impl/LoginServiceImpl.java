package com.qf.service.impl;

import com.qf.dao.LoginMapper;
import com.qf.pojo.SysUser;
import com.qf.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * “用户登录”服务层实现
 * */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginMapper loginMapper;
    /**
     * shiro认证用户：获取用户信息
     * */
    @Override
    public SysUser getUserByName(String username) {
        return this.loginMapper.getUserByName(username);
    }
}
