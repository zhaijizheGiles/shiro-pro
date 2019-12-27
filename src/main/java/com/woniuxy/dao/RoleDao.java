package com.woniuxy.dao;

import com.woniuxy.pojo.Role;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface RoleDao {
    public List<Role> getRoleList();
    public void addRole(Role role);
    public void updateRole(Role role);
    public List<Role> getRolesByUserId(Integer userId);
    public void addRoleMenu(Map<String,Integer> params);
    public void deleteRoleMenu(Integer roleId);
}
