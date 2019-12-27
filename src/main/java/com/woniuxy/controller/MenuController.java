package com.woniuxy.controller;

import com.woniuxy.bean.TreeNode;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/menu")
public class MenuController {
    @RequestMapping("list.html")
    public String list(){
        return "/menu_list";
    }
    @RequestMapping("go_edit.html")
    @RequiresPermissions("menu:edit")
    public String goEdit(){
        return "menu_edit";
    }
}
