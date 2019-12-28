package com.qf.controller;

import com.qf.pojo.SysUser;
import com.qf.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
/**
 * description:
 * 测试数据库连接是否成功
 * @author zhangqi
 * 2019/12/26
 * */
@Controller
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;
    @ResponseBody
    @RequestMapping("/findAll")
    public List<SysUser> findAll(){
        return sysUserService.findAll();
    }
}
