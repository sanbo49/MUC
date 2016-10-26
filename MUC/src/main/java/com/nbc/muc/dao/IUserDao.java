package com.nbc.muc.dao;

import java.util.List;

import com.nbc.muc.pojo.User;


public interface IUserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);
    
    List<User> selectAll();
    
    List<User> queryUsers(String sql);
    
    
    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}