package com.nbc.muc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.nbc.muc.pojo.User;


public interface IUserDao {
	
	
	
    @Select("select id, user_name, password, age from user_t ")
    public List<User> selectAll();
     
    @Insert("insert into user_t (user_name, password, age)values(#{userName},md5(#{password}),#{age})")
    public int insert(User po);
	
	@Delete("delete from user_t where id=#{id}")
    int deleteByPrimaryKey(int id);

//    int insert(User record);

    int insertSelective(User record);
    @Select("select id, user_name, password, age from user_t where id=#{id}")
    User selectByPrimaryKey(Integer id);
    
//    List<User> selectAll();
    
    List<User> queryUsers(String sql);
    
    
    int updateByPrimaryKeySelective(User record);
    @Update("update user_t set user_name=#{userName},age=#{age} where  id=#{id}")
    int updateByPrimaryKey(User user);

    @Select("select id, user_name, password, age from user_t where user_name =#{name}")
	public User getByAccount( String name);
}