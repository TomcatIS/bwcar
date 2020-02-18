package com.qf.service;

import com.qf.dto.DataGridResult;
import com.qf.dto.QueryDTO;
import com.qf.pojo.SysMenu;
import com.qf.util.R;

import java.util.List;
import java.util.Map;

/**
 * description:
 * 菜单服务层
 * @author zhangqi
 * @created:2020/1/5
 * */
public interface MenuService {
    // 根据菜单查询条件返回查询结果
    DataGridResult findMenu(QueryDTO queryDTO);
    // 删除菜单
    R deleteMenu(List<Long> ids);

    List<SysMenu> selectMenu();
    // 新增菜单
    R saveMenu(SysMenu sysMenu);

    /**根据用户ID查询用户菜单*/
    Map<String ,Object> listUserMenusByUserId(Long userId);

    /**查询用户按钮权限*/
    List<String> listButtonPermsByUserId(Long userId);
}
