package com.qf.service;

import com.qf.dto.DataGridResult;
import com.qf.dto.QueryDTO;
import com.qf.pojo.SysUser;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

public interface SysUserService {

    /** “用户管理”：查询所有用户 */
    DataGridResult listAllUsers(QueryDTO queryDTO);

    /** “用户管理”：excel形式导出用户信息 */
    Workbook exportUserInfo();

    SysUser getUserByName(String username);
}
