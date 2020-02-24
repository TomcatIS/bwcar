package com.qf.service;

import com.qf.dto.DataGridResult;
import com.qf.dto.QueryDTO;
import com.qf.pojo.SysUser;
import com.qf.util.R;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

public interface SysUserService {

    /** “用户管理”：查询所有用户 */
    DataGridResult listAllUsers(QueryDTO queryDTO);

    /**“用户管理”：删除用户 */
    R deleteUsers(List<Long> ids);

    /**“用户管理”：添加用户 */
    R addUser(SysUser sysUser);

    /**“用户管理”：修改用户信息 */
    R updateUserInfo(SysUser sysUser);

    /** “用户管理”：excel形式导出用户信息 */
    Workbook exportUserInfo();

    /** shiro认证用户：获取用户信息 */
    SysUser getUserByName(String username);
}
