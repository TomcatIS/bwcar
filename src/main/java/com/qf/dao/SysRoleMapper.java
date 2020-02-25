package com.qf.dao;


import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysRoleMapper {

    List<String> listRolesByUserId(@Param("userId") Long userId);

    Long getUserIdByUserName(String username);
}