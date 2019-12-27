package com.woniuxy.dao;

import com.woniuxy.bean.TreeNode;
import com.woniuxy.pojo.Menu;
import com.woniuxy.pojo.MenuPermission;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MenuDao {
    /**
     * 通过角色id查找菜单集合
     * @param roleId
     * @return
     */
    public List<Menu> getMenusByRoleId(Integer roleId);

    /**
     * 得到所有的菜单
     * @return
     */
    public List<Menu> getAllMenus();

    public List<MenuPermission> getMenuPerms();
}
