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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Override
    public List<String> listMenuPermsByUserId(Long userId) {
        List<String> perms = this.sysMenuMapper.listMenuPermsByUserId(userId);
        Set<String> hashset = new HashSet<>();
        for (String perm : perms){
            if (perm != null && !perm.equals("")) {
                String[] permArr = perm.split(",");
                for (String x : permArr){
                    hashset.add(x);
                }
            }
        }
        List<String> permsList = new ArrayList<>();
        permsList.addAll(hashset);
        return permsList;
    }
}
