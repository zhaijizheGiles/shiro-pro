package com.woniuxy.service;

import com.woniuxy.bean.TreeNode;
import com.woniuxy.pojo.Menu;
import com.woniuxy.pojo.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    public User doLogin(String userName, String password);
    public User getUserByUserName(String userName);
    public List<TreeNode> getTreeNodeList();
    public Integer[] getRolesByUserId(Integer userId);
}
