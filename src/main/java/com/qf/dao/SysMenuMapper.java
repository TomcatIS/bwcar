package com.qf.dao;

import com.qf.dto.QueryDTO;
import com.qf.pojo.SysMenu;
import com.qf.pojo.SysMenuExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface SysMenuMapper {

    /** 根据用户ID查询用户一级菜单 */
    List<Map<String, Object>> listDirsByUserId(@Param("userId")Long userId);

    /** 查询一级目录对应的二级菜单 */
    List<Map<String, Object>> listSubMenusOfDirs(@Param("userId")Long userId, @Param("parentId")Long parentId);

    /** 查询用按钮权限 */
    List<String> listButtonPermsByUserId(@Param("userId")Long userId);

    /**“菜单管理”:显示菜单信息 */
    List<SysMenu> listMenusInfo(QueryDTO queryDTO);

    /**“菜单管理”:删除菜单信息 */
    int deleteMenusInfo(List<Long> ids);

    /**“菜单管理”：生成树形菜单 */
    List<SysMenu> generateZtree();

    /**“菜单管理”：新增菜单信息 */
    int addMenuInfo(SysMenu sysMenu);

    /**“菜单管理”：修改菜单信息 */
    int updateMenuInfo(SysMenu sysMenu);

}