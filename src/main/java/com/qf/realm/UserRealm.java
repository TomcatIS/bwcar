package com.qf.realm;

import com.qf.pojo.SysUser;
import com.qf.service.LoginService;
import com.qf.service.MenuService;
import com.qf.service.RoleService;
import com.qf.service.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zhangqi
 * @created 2020/1/15
 * */
@Component
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private MenuService menuService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private LoginService loginService;

    /**
     * 授权
     * */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 获取用户
        SysUser user = (SysUser) principalCollection.getPrimaryPrincipal();
        Long userId = user.getUserId();
        // 查询用户角色
        List<String> roles = this.roleService.listRolesByUserId(userId);
        // 查询用户按钮权限
        List<String> perms = this.menuService.listButtonPermsByUserId(userId);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRoles(roles);
        simpleAuthorizationInfo.addStringPermissions(perms);
        return simpleAuthorizationInfo;
    }

    /**
     * 认证
     * */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)authenticationToken;
        String username = usernamePasswordToken.getUsername();
        String password = new String(usernamePasswordToken.getPassword());
        SysUser user = this.loginService.getUserByName(username);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, this.getName());
        return info;
    }
}
