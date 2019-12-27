package com.woniuxy.controller;

import com.woniuxy.bean.TreeNode;
import com.woniuxy.pojo.Menu;
import com.woniuxy.pojo.User;
import com.woniuxy.service.UserService;
import com.woniuxy.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.annotation.RequestScope;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "goLogin.html")
    public String goLogin(){
        return "login";
    }
    @RequestMapping(value = "/login.html")
    public String login(String userName, String password, HttpServletRequest request){
        User user=userService.doLogin(userName,password);
        if(user==null){
            request.setAttribute("error","用户名或密码有误!");
            return "login";
        }
        request.getSession().setAttribute(Constants.SESSION_USER,user);
        return "redirect:index.html";
    }
    @RequestMapping(value = "/userMenus")
    @ResponseBody
    public List<TreeNode> userMenus(HttpServletRequest request){
        //获取用户对象
        User user=(User)request.getSession().getAttribute(Constants.SESSION_USER);
        //List<TreeNode> menuList=userService.getMenusByUserId(user.getId());
        List<TreeNode> menuList=userService.getTreeNodeList();
        return menuList;
    }
}
