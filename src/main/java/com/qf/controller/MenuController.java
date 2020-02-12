package com.qf.controller;

import com.qf.dto.DataGridResult;
import com.qf.dto.QueryDTO;
import com.qf.pojo.SysMenu;
import com.qf.pojo.SysUser;
import com.qf.service.MenuService;
import com.qf.util.R;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description:
 * 菜单显示控制层
 * @author zhangqi
 * @created 2020/1/5
 * */
@Controller
public class MenuController {
    @Autowired
    private MenuService menuService;

    /**
     * 显示菜单
     */
    @RequestMapping("/sys/menu/list")
    @ResponseBody
    @RequiresPermissions("sys:menu:list")
    public DataGridResult findMenu(QueryDTO queryDTO){
        DataGridResult menu = this.menuService.findMenu(queryDTO);
        return menu;
    }
    /**
     * 删除菜单
     * */
    @RequestMapping("/sys/menu/del")
    @ResponseBody
    public R deleteMenu(@RequestBody List<Long> ids){
        return this.menuService.deleteMenu(ids);
    }
    /**
     * 新增菜单时，显示树形菜单
     * */
    @RequestMapping("/sys/menu/select")
    @ResponseBody
    public Map findMenu(){
        List<SysMenu> menuList = this.menuService.selectMenu();
        HashMap<String, List> map = new HashMap<>();
        map.put("menuList", menuList);
        return map;
    }
    /**
     * 保存新增菜单信息
     * */
    @RequestMapping("/sys/menu/save")
    @ResponseBody
    public R saveMenu(@RequestBody SysMenu sysMenu){
        return this.menuService.saveMenu(sysMenu);
    }

    /**
     * 获取用户菜单
     * */
    @RequestMapping("/sys/menu/user")
    @ResponseBody
    public Map<String, Object> getUserMenus(){
        SysUser user = (SysUser)SecurityUtils.getSubject().getPrincipal();
        Map<String, Object> userMenus = this.menuService.findUserMenus(user.getUserId());
        return userMenus;
    }
}
