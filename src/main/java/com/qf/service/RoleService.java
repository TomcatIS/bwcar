package com.qf.service;

import java.util.List;

public interface RoleService {
    // 根据用户名查询用户ID
    Long getUserIdByUserName(String username);
    List<String> listRolesByUserId(Long userId);

}
