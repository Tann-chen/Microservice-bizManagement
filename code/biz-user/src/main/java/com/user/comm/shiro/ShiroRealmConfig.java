package com.user.comm.shiro;


import com.user.domain.entity.Permission;
import com.user.domain.entity.Role;
import com.user.domain.entity.User;
import com.user.service.UserInfoService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;


public class ShiroRealmConfig extends AuthorizingRealm {

    @Autowired
    private UserInfoService userInfoService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User accessUser = (User) principals.getPrimaryPrincipal();
        for (Role role : accessUser.getRoleList()) {
            //add role info into authorizationInfo
            authorizationInfo.addRole(role.getRole());
            for (Permission permission : role.getPermissionList()) {
                //permission1:add permission expression into authorizationInfo
                authorizationInfo.addStringPermission(permission.getPermission());
            }
        }
        //permission2:add direct permission into authorizationInfo
        for (Permission permission : accessUser.getPermissionList()) {
            authorizationInfo.addStringPermission(permission.getPermission());
        }

        return authorizationInfo;
    }


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String loginEmail = (String) token.getCredentials();
        User loginUser = userInfoService.findUserByEmail(loginEmail);
        if(null == loginUser){
            return null;
        }
        //set passed credential into authenticationInfo for checking(Shiro's implementation)
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                loginUser,
                loginUser.getPassword(),
                ByteSource.Util.bytes(loginUser.getPwdSalt()),
                getName()
        );

        return authenticationInfo;
    }

}
