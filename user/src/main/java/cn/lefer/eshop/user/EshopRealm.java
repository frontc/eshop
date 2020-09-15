package cn.lefer.eshop.user;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;


public class EshopRealm extends AuthorizingRealm {
    private UserService userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userName = (String) super.getAvailablePrincipal(principalCollection);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        User user = userService.loadUserByUserName(userName);
        if (user == null) {
            throw new UnknownAccountException("Username " + userName + " not exists.");
        }
        info.setRoles(user.getRoles());
        info.setStringPermissions(user.getPermissions());
        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String userName = usernamePasswordToken.getUsername();
        if (userName == null) {
            throw new AccountException("Username should not be empty!");
        }
        User user = userService.loadUserByUserName(userName);
        if (user == null) {
            throw new UnknownAccountException("Username " + userName + " not exists.");
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(), user.getUserName());
        simpleAuthenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(user.getSalt()));
        return simpleAuthenticationInfo;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
