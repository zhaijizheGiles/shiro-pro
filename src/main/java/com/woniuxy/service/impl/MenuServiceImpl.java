package com.woniuxy.service.impl;

import com.woniuxy.dao.MenuDao;
import com.woniuxy.pojo.Menu;
import com.woniuxy.pojo.MenuPermission;
import com.woniuxy.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService{
    @Autowired
    private MenuDao menuDao;
    @Override
    public List<MenuPermission> getMenuPerms() {
        return menuDao.getMenuPerms();
    }
}
