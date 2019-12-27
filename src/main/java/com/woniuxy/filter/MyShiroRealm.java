package com.woniuxy.filter;

import com.woniuxy.bean.TreeNode;
import com.woniuxy.pojo.Menu;
import com.woniuxy.pojo.User;
import com.woniuxy.service.UserService;
import com.woniuxy.util.Constants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class MyShiroRealm extends AuthorizingRealm{
    @Autowired
    private UserService userService;
    //每次验证权限的时候执行
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("===============验证权限的时候被调用===================");
        Subject subject=SecurityUtils.getSubject();
        Session session=subject.getSession();
        User sessionUser= (User) session.getAttribute(Constants.SESSION_USER);
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        Integer[] roleIds=userService.getRolesByUserId(sessionUser.getId());
        Set<String> roleList=new HashSet<>();
        for(int i=0;i<roleIds.length;i++){
            roleList.add(String.valueOf(roleIds[i]));
        }
        info.addRoles(roleList);
        return info;
    }
    //登录执行
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("***********用户认证的时候被调用******************");
        String userName=(String)authenticationToken.getPrincipal();
        User user=userService.getUserByUserName(userName);
        if(user!=null){
            return new SimpleAuthenticationInfo(userName,user.getPassword(),null,getName());
        }
        return null;
    }
}
