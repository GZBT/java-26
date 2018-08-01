package com.kaisheng.it.shiro;

import com.kaisheng.it.entity.Employee;
import com.kaisheng.it.entity.EmployeeLoginLog;
import com.kaisheng.it.entity.Permission;
import com.kaisheng.it.entity.Role;
import com.kaisheng.it.service.EmployeeService;
import com.kaisheng.it.service.RolePermissionService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * @author guojiabang
 * @date 2018/7/30 0030
 */
public class MyRealm extends AuthorizingRealm {

    Logger logger = LoggerFactory.getLogger(MyRealm.class);

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private RolePermissionService rolePermissionService;

    /**
     * 判断权限角色  授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取当前登录的对象
        Employee employee = (Employee) principalCollection.getPrimaryPrincipal();
        //获取当前登录对象拥有的角色
        List<Role> roleList = rolePermissionService.findRoleByAccountId(employee.getId());
        //获取当前登录对象拥有的权限
        List<Permission> permissionList = new ArrayList<>();
        for(Role role : roleList) {
            List<Permission> permissions = rolePermissionService.findAllPermissionByRoleById(role.getId());
            permissionList.addAll(permissions);
        }

        Set<String> roleCodeSet = new HashSet<>();
        for(Role role : roleList) {
            roleCodeSet.add(role.getRoleCode());
        }

        Set<String> permissionCodeSet = new HashSet<>();
        for(Permission per : permissionList) {
            permissionCodeSet.add(per.getPermissionCode());
        }

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //当前用户拥有的角色（code）
        simpleAuthorizationInfo.setRoles(roleCodeSet);
        //当前用户拥有的权限(code)
        simpleAuthorizationInfo.setStringPermissions(permissionCodeSet);
        return simpleAuthorizationInfo;
    }
    /**
     * 判断登录
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String userTel = usernamePasswordToken.getUsername();

        Employee employee = employeeService.findEmployeeByTel(userTel);


        if(employee == null){
            throw new UnknownAccountException("用户名或者密码错误");
        } else {
            if(employee.getState().equals(Employee.EMPLOYEE_STATE_FROZEN)){
                throw new LockedAccountException("账号已冻结");
            }  else {
                // 登录成功
                String loginId = usernamePasswordToken.getHost();

                // 记录日志
                EmployeeLoginLog employeeLoginLog = new EmployeeLoginLog();
                employeeLoginLog.setLoginIp(loginId);
                employeeLoginLog.setLoginTime(new Date());
                employeeLoginLog.setEmployeeId(employee.getId());

                employeeService.saveLoginLog(employeeLoginLog);

                logger.info("{}-{} 在 {} 登录了系统", employee.getEmployeeName(),employee.getEmployeeTel(),new Date());

                return new SimpleAuthenticationInfo(employee,employee.getPassword(),getName());
            }
        }

    }
}
