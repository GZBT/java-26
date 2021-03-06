package com.kaishengit.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * @author guojiabang
 * @date 2018/7/30 0030
 */
public class MyRealm implements Realm {

    @Override
    public String getName() {
        return "myRealm";
    }

    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        return authenticationToken instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String username = usernamePasswordToken.getUsername();
        String password = new String(usernamePasswordToken.getPassword());

        if(!username.equals("admin")){
            throw new UnknownAccountException("没有该账户");
        }

        if(!password.equals("1231231")){
            throw new IncorrectCredentialsException("密码错误");
        }

        return new SimpleAuthenticationInfo(username,password,getName());
    }
}
