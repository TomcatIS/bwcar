package com.qf.dao;

import com.qf.dto.QueryDTO;
import com.qf.pojo.SysUser;
import com.qf.pojo.SysUserExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
/**
 * 用户管理功能持久层接口
 * */
public interface SysUserMapper {
    int countByExample(SysUserExample example);

    int deleteByExample(SysUserExample example);

    int deleteByPrimaryKey(Long userId);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    List<SysUser> selectByExample(SysUserExample example);

    SysUser selectByPrimaryKey(Long userId);

    int updateByExampleSelective(@Param("record") SysUser record, @Param("example") SysUserExample example);

    int updateByExample(@Param("record") SysUser record, @Param("example") SysUserExample example);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    /**“用户管理”：查询所有用户*/
    List<SysUser> listAllUsers(QueryDTO queryDTO);

    /** “用户管理”：excel导出用户信息*/
    List<Map<String, Object>> exportUserInfo();

    SysUser getUserByName(String username);
}