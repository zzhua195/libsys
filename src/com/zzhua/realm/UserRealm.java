package com.zzhua.realm;

import com.zzhua.domain.Perm;
import com.zzhua.domain.Role;
import com.zzhua.domain.User;
import com.zzhua.service.PermService;
import com.zzhua.service.RoleService;
import com.zzhua.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Copyright (C), 2019, 深圳太极云软技术有限公司
 * Author：   zzhua
 * Created by Administrator on 2020/1/29
 * <p>
 * Description:
 */


public class UserRealm extends AuthorizingRealm{

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermService permService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        User user = userService.loginUser(username);
        if(null==user){
            return null;
        }
        ByteSource salt = ByteSource.Util.bytes((username + "abcd").getBytes());
        SimpleAuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user, user.getPassword(),salt, user.getUsername());
        return authcInfo;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorInfo = new SimpleAuthorizationInfo();
        List<Role> roleList = roleService.selectRolesByUid(user.getId());
        List<Perm> permList = permService.selectPermsByUid(user.getId());
        for (Role role : roleList) {
            authorInfo.addRole(role.getRolename());
        }
        for (Perm perm : permList) {
            authorInfo.addStringPermission(perm.getPermcode());
        }
        return authorInfo;
    }


}
