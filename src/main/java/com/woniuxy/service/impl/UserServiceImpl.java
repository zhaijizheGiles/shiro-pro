package com.woniuxy.service.impl;

import com.woniuxy.bean.TreeNode;
import com.woniuxy.dao.MenuDao;
import com.woniuxy.dao.RoleDao;
import com.woniuxy.dao.UserDao;
import com.woniuxy.pojo.Menu;
import com.woniuxy.pojo.Role;
import com.woniuxy.pojo.User;
import com.woniuxy.service.UserService;
import com.woniuxy.util.Constants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.reflect.generics.tree.Tree;


import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private MenuDao menuDao;
    @Override
    public User doLogin(String userName, String password) {
        Subject subject= SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(userName,password);
        try {
            subject.login(token);
            Session session=subject.getSession();
            User user=getUserByUserName(userName);
            session.setAttribute(Constants.SESSION_USER,user);
            return user;
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public User getUserByUserName(String userName) {
        return userDao.getUserByUserName(userName);
    }

    @Override
    public List<TreeNode> getTreeNodeList() {
        List<Menu> menus=this.menuDao.getAllMenus();
        List<TreeNode> treeNodes=new ArrayList<>();
        Map<Integer,TreeNode> treeNodeMap=new HashMap<>();
        for(Menu menu:menus){
            TreeNode treeNode=new TreeNode();
            treeNode.setId(menu.getId());
            treeNode.setText(menu.getName());
            treeNode.setIconCls(menu.getIcon());
            Map<String,String> attributes=new HashMap<>();
            attributes.put("url",menu.getUrl());
            treeNode.setAttributes(attributes);
            treeNodeMap.put(menu.getId(),treeNode);
            if(menu.getpId()==1){
                treeNodes.add(treeNode);
            }
        }
        for(Menu menu:menus){
            if(menu.getpId()!=1){
                TreeNode parent=treeNodeMap.get(menu.getpId());
                TreeNode secondNode=treeNodeMap.get(menu.getId());
                parent.getChildren().add(secondNode);
            }
        }
        return treeNodes;
    }

    @Override
    public Integer[] getRolesByUserId(Integer userId){
        return this.userDao.getRolesByUserId(userId);
    }
}
