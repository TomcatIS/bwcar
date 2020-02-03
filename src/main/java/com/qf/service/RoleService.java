package com.qf.service;

import java.util.List;

public interface RoleService {
    List<String> listRolesByUserId(Long userId);

}
