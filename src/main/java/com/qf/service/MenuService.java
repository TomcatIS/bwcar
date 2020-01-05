package com.qf.service;

import com.qf.dto.DataGridResult;
import com.qf.dto.QueryDTO;
import com.qf.util.R;

import java.util.List;

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
}
