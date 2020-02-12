package com.qf.service.impl;

import com.qf.dao.SysRoleMapper;
import com.qf.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;

    /**
     * 根据用户名查询用户ID
     **/
    @Override
    public Long getUserIdByUserName(String username) {
        return this.sysRoleMapper.getUserIdByUserName(username);
    }

    @Override
    public List<String> listRolesByUserId(Long userId) {
        return this.sysRoleMapper.listRolesByUserId(userId);
    }
}
