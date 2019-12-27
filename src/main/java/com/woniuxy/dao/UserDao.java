package com.woniuxy.dao;

import com.woniuxy.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    public User getUserByUserName(String userName);
    public Integer[] getRolesByUserId(Integer userId);
}
