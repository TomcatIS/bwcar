package com.qf.service.impl;

import com.qf.dao.SysUserMapper;
import com.qf.pojo.SysUser;
import com.qf.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Override
    public List<SysUser> findAll() {
        return sysUserMapper.selectByExample(null);
    }
}
