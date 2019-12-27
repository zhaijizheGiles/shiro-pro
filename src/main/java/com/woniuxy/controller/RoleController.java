package com.woniuxy.controller;

import com.woniuxy.bean.TreeNode;
import com.woniuxy.pojo.Role;
import com.woniuxy.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("role.html")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @RequestMapping(params = "act=goList")
    public String goRoleList(){
        return "role_list";
    }
    @RequestMapping(params = "act=list")
    @ResponseBody
    public List<Role> list(){
        return roleService.getRoleList();
    }
    @RequestMapping(params = "act=menu_list")
    @ResponseBody
    public List<TreeNode> menuList(@RequestParam Integer roleId){
        return this.roleService.getTreeNodeList(roleId);
    }
    @RequestMapping(params = "act=assignMenu")
    @ResponseBody
    public Map<String,String> assignMenu(Integer roleId,Integer[] menuIds){
        Map<String,String> map=new HashMap<String,String>();
        boolean flag=this.roleService.doAssignMenuByRoleId(roleId,menuIds);
        if(flag){
            map.put("msg","success");
        }
        return map;
    }
}
