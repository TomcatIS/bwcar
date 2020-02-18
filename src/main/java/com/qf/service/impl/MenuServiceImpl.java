package com.qf.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.dao.SysMenuMapper;
import com.qf.dto.DataGridResult;
import com.qf.dto.QueryDTO;
import com.qf.pojo.SysMenu;
import com.qf.service.MenuService;
import com.qf.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 创建者：zhangqi
 * 修改时间：2020/2/17
 * */
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private SysMenuMapper sysMenuMapper;
    
    @Override
    public DataGridResult findMenu(QueryDTO queryDTO) {
        PageHelper.offsetPage(queryDTO.getOffset(), queryDTO.getLimit());
        if (queryDTO.getSort() != null && !queryDTO.getSort().equals("")){
            queryDTO.setSort("menu_id");
        }
        List<SysMenu> menuByPage = this.sysMenuMapper.findMenuByPage(queryDTO);
        PageInfo<SysMenu> pageInfo = new PageInfo<SysMenu>(menuByPage);
        long total = pageInfo.getTotal();
        // pageInfo.getList()因该是返回一页的数据，但打印出来没看到封装的信息
        DataGridResult dataGridResult = new DataGridResult(total, pageInfo.getList());
        return dataGridResult;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public R deleteMenu(List<Long> ids) {
        for (Long x : ids){
            if (x < 51){
                return R.error("删除失败：不能删除系统菜单");
            }
        }
        int i = this.sysMenuMapper.deleteMenu(ids);
        if (i > 0){
            return R.ok("删除成功");
        }else {
            return R.error("未知原因，删除失败");
        }
    }

    @Override
    public List<SysMenu> selectMenu() {
        List<SysMenu> menuList = this.sysMenuMapper.findMenu();
        SysMenu sysMenu = new SysMenu();
        sysMenu.setName("一级菜单");
        sysMenu.setParentId(-1L);
        sysMenu.setMenuId(0L);
        sysMenu.setType(0);
        menuList.add(sysMenu);
        return menuList;
    }
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public R saveMenu(SysMenu sysMenu) {
        int i = this.sysMenuMapper.saveMenu(sysMenu);
        if (i > 0){
            return R.ok("添加成功");
        }else{
            return R.error("添加失败");
        }
    }

    /**
     * 查询用户自己的菜单
     * */
    @Override
    public Map<String, Object> listUserMenusByUserId(Long userId) {
        Map<String, Object> menuMap =  new HashMap<>();
        // 根据用户id查询一级目录
        List<Map<String, Object>> dirs = this.sysMenuMapper.listDirsByUserId(userId);
        // 查询一级目录对应的二级菜单
        Long parentId;
        List<Map<String, Object>> subMenus;
        for (Map<String, Object> dir : dirs) {
            parentId = Long.parseLong(dir.get("menuId") + "");
            subMenus = this.sysMenuMapper.listSubMenusOfDirs(userId, parentId);
            dir.put("list", subMenus);
        }
        menuMap.put("menuList", dirs);
        // 查询用户菜单权限
        List<String> permsList = listButtonPermsByUserId(userId);
        menuMap.put("permissions", permsList);
        return menuMap;
    }

    /**
     * 查询用户按钮权限
     * */
    @Override
    public List<String> listButtonPermsByUserId(Long userId) {
        List<String> perms = this.sysMenuMapper.listButtonPermsByUserId(userId);
        Set<String> hashSet = new HashSet<>();
        for (String perm : perms) {
            if (perm != null && !perm.equals("")) {
                String[] splits = perm.split(",");
                for (String split : splits) {
                    hashSet.add(split);
                }
            }
        }
        List<String> permsList = new LinkedList<>();
        permsList.addAll(hashSet);
        return permsList;
    }
}