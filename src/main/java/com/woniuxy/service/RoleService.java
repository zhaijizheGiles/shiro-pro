package com.woniuxy.service;

import com.woniuxy.bean.TreeNode;
import com.woniuxy.pojo.Menu;
import com.woniuxy.pojo.Role;

import java.util.List;

public interface RoleService {
    public List<Role> getRoleList();
    public void addRole(Role role);
    public void updateRole(Role role);
    public List<TreeNode> getTreeNodeList(Integer roleId);
    //分配权限
    public boolean doAssignMenuByRoleId(Integer roleId,Integer[] menuIds);
}
