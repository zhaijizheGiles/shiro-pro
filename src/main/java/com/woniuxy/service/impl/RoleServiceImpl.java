package com.woniuxy.service.impl;

import com.woniuxy.bean.TreeNode;
import com.woniuxy.dao.MenuDao;
import com.woniuxy.dao.RoleDao;
import com.woniuxy.pojo.Menu;
import com.woniuxy.pojo.Role;
import com.woniuxy.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private MenuDao menuDao;
    @Override
    public List<Role> getRoleList() {
        return this.roleDao.getRoleList();
    }

    @Override
    public void addRole(Role role) {
        this.roleDao.addRole(role);
    }

    @Override
    public void updateRole(Role role) {
        this.roleDao.updateRole(role);
    }

    @Override
    public List<TreeNode> getTreeNodeList(Integer roleId) {
        List<Menu> roleMenusList=menuDao.getMenusByRoleId(roleId);
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
                for (Menu menuItem:roleMenusList){
                    if(menu.getName().equals(menuItem.getName())){
                        secondNode.setChecked(true);
                    }
                }
                parent.getChildren().add(secondNode);
            }
        }
        return treeNodes;
    }
    @Override
    public boolean doAssignMenuByRoleId(Integer roleId, Integer[] menuIds) {
        boolean flag=false;
        try {
            this.roleDao.deleteRoleMenu(roleId);//删除t_role_menu表中所有roleId的记录
            for(Integer menuId:menuIds){
                Map<String,Integer> params=new HashMap<>();
                params.put("roleId",roleId);
                params.put("menuId",menuId);
                this.roleDao.addRoleMenu(params);
               flag=true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
}
