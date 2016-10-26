package com.nbc.muc.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageHelper;
import com.nbc.common.PagedResult;
import com.nbc.muc.pojo.User;

public interface IUserService {
	public User getUserById(int userId);

	public PagedResult<User> getUserAll(Integer pageNumber,  
			Integer pageSize);
	
	public PagedResult<User> queryUsers(Integer pageNumber,  
			Integer pageSize,String sql);
	
}
