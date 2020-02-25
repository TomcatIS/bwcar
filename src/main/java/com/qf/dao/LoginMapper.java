package com.qf.dao;

import com.qf.pojo.SysUser;

/**
 * “用户登录”持久层接口
 * */
public interface LoginMapper {

    /** shiro认证：获取用户信息 */
    SysUser getUserByName(String username);
}
