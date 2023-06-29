package com.psq.shiroexercise.config;

import com.psq.shiroexercise.dto.UserInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;

public class AuthRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        UserInfo userInfo = (UserInfo) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if (userInfo != null) {
            info.setRoles(userInfo.getRoles());
            info.setStringPermissions(userInfo.getPermissions());
        }
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(123456);
        userInfo.setRoles(new HashSet<>() {{
            add("admin");
        }});
        userInfo.setPermissions(new HashSet<>() {{
            add("system:user:select");
            add("system:user:delete");
        }});
        return new SimpleAuthenticationInfo(userInfo, userInfo.getUserId(), getName());
    }
}
