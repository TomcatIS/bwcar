package com.qf.dao;

import com.qf.dto.QueryDTO;
import com.qf.pojo.SysUser;

import java.util.List;
import java.util.Map;

/**
 * “用户管理”功能持久层接口
 * */
public interface SysUserMapper {

    /**“用户管理”：显示所有用户 */
    List<SysUser> listAllUsers(QueryDTO queryDTO);

    /**“用户管理”：删除用户 */
    int deleteUsers(List<Long> ids);

    /**“用户管理”：添加用户 */
    int addUser(SysUser sysUser);

    /**“用户管理”：修改用户信息 */
    int updateUserInfo(SysUser sysUser);

    /** “用户管理”：excel导出用户信息 */
    List<Map<String, Object>> exportUserInfo();
}