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

    /** 根据用户ID查询用户菜单 */
    Map<String ,Object> listUserMenusByUserId(Long userId);

    /** 查询用户按钮权限 */
    List<String> listButtonPermsByUserId(Long userId);

    /** “菜单管理”:显示菜单信息 */
    DataGridResult listMenusInfo(QueryDTO queryDTO);

    /** “菜单管理”:删除菜单信息 */
    R deleteMenusInfo(List<Long> ids);

    /**“菜单管理”：生成树形菜单 */
    Map generateZtree();

    /**“菜单管理”：新增菜单信息 */
    R addMenuInfo(SysMenu sysMenu);

    /**“菜单管理”：修改菜单信息 */
    R updateMenuInfo(SysMenu sysMenu);

}
