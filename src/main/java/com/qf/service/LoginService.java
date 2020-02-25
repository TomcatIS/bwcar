package com.qf.service;

import com.qf.pojo.SysUser;

/**
 * “用户登录”服务层接口
 * @author zhangqi
 * 创建时间：2020/2/25
 * */
public interface LoginService {

    /** shiro认证：获取用户信息 */
    SysUser getUserByName(String username);
}
