package com.qf.controller;

import com.qf.dto.DataGridResult;
import com.qf.dto.QueryDTO;
import com.qf.service.MenuService;
import com.qf.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
}
