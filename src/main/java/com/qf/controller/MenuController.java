package com.qf.controller;

import com.qf.dto.DataGridResult;
import com.qf.dto.QueryDTO;
import com.qf.log.MyLog;
import com.qf.pojo.SysMenu;
import com.qf.pojo.SysUser;
import com.qf.service.MenuService;
import com.qf.util.R;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description:
 * "菜单管理"功能控制层
 * @author zhangqi
 * @created 2020/1/5
 * */
@Controller
public class MenuController {
    @Autowired
    private MenuService menuService;

    /**
     * 获取用户菜单
     * */
    @RequestMapping("/sys/menu/user")
    @ResponseBody
    public Map<String, Object> getUserMenus() {
        SysUser user = (SysUser)SecurityUtils.getSubject().getPrincipal();
        Map<String, Object> userMenus = this.menuService.listUserMenusByUserId(user.getUserId());
        return userMenus;
    }

    /**
     *”菜单管理”：显示所有菜单信息
     **/
    @MyLog("菜单列表")
    @RequestMapping("/sys/menu/list")
    @ResponseBody
    @RequiresPermissions("sys:menu:list")
    public DataGridResult listMenusInfo(QueryDTO queryDTO) {
        DataGridResult dataGridResult = this.menuService.listMenusInfo(queryDTO);
        return dataGridResult;
    }

    /**
     * "菜单管理":删除菜单信息
     * */
    @MyLog("菜单删除")
    @RequestMapping("/sys/menu/del")
    @ResponseBody
    public R deleteMenusInfo(@RequestBody List<Long> ids) {
        return this.menuService.deleteMenusInfo(ids);
    }

    /**
     * “菜单管理”：生成树形菜单
     * */
    @RequestMapping("/sys/menu/generateZtree")
    @ResponseBody
    public Map generateZtree(){
        return this.menuService.generateZtree();
    }

    /**
     * “菜单管理”：新增菜单信息
     * */
    @RequestMapping("/sys/menu/add")
    @ResponseBody
    public R addMenuInfo(@RequestBody SysMenu sysMenu){
        return this.menuService.addMenuInfo(sysMenu);
    }

    /**
     * “菜单管理”：修改菜单信息
     * */
    @RequestMapping("/sys/menu/update")
    @ResponseBody
    public R updateMenuInfo(@RequestBody SysMenu sysMenu){
        return this.menuService.updateMenuInfo(sysMenu);
    }

}
