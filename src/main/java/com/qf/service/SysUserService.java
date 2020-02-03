package com.qf.service;

import com.qf.dto.DataGridResult;
import com.qf.dto.QueryDTO;
import com.qf.pojo.SysUser;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

public interface SysUserService {
    DataGridResult selectAllUser(QueryDTO queryDTO);

    // 导出用户
    Workbook exportUser();

    SysUser getUserByName(String username);
}
