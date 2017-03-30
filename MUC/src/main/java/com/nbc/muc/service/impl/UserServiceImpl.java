package com.nbc.muc.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.mortbay.log.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.nbc.common.BeanUtil;
import com.nbc.common.PagedResult;
import com.nbc.common.log.MethodLog;
import com.nbc.muc.dao.IUserDao;
import com.nbc.muc.pojo.User;
import com.nbc.muc.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {
	@Resource
	private IUserDao userDao;
	@Resource
	private JdbcTemplate jdbcTemplate;
	private  Logger logger=Logger.getLogger(this.getClass());
//	@Resource(name="redisTemplate")
//	private RedisTemplate<Serializable, Serializable> redisTemplate;
	
//	@Override
	
	@Cacheable(value="accountCache")// 缓存名叫 accountCache 
	public User getUserById(int userId) {
		// TODO Auto-generated method stub
		return this.userDao.selectByPrimaryKey(userId);
	}
	@MethodLog(remark = "查询人员ServiceImpl")  
	public PagedResult<User>  getUserAll(Integer pageNo,  
			Integer pageSize) {
		// TODO Auto-generated method stub
		pageNo = pageNo == null?1:pageNo;
		pageSize = pageSize == null?10:pageSize;
		PageHelper.startPage(pageNo,pageSize);  //startPage是告诉拦截器说我要开始分页了。分页参数是这两个。
		return BeanUtil.toPagedResult(userDao.selectAll());
	}
	
	public String createHistoryJson(String methodName,Object[] method_param,Object returnValue){
		logger.info(method_param.length+"");
		return "service中的methodName:"+methodName+" returnValue:"+returnValue;
	}
	
	
	public PagedResult<User>  queryUsers(Integer pageNo,  
			Integer pageSize,String sql) {
		// TODO Auto-generated method stub
//		redisTemplate.execute(new RedisCallback<Boolean>() {
//
//			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
//				// TODO Auto-generated method stub
//				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();  
//				byte[] key = redisTemplate.getStringSerializer().serialize("name");
//                if (connection.exists(key)) {
//                    byte[] value = connection.get(key);
//                    String name = redisTemplate.getStringSerializer().deserialize(value);
//    				Log.info("name:"+name);
//                }
//                return true;
//				
//			}
//		});
		pageNo = pageNo == null?1:pageNo;
		pageSize = pageSize == null?10:pageSize;
		PageHelper.startPage(pageNo,pageSize);  //startPage是告诉拦截器说我要开始分页了。分页参数是这两个。
		return BeanUtil.toPagedResult(userDao.queryUsers(sql));
	}

	
	public int insert(User user) {
		return userDao.insert(user);
		
	}
	@CacheEvict(value="accountCache",beforeInvocation=true)
	public int update(User user) {
		return userDao.updateByPrimaryKey(user);
	}
	/* (non-Javadoc)
	 * @see com.nbc.muc.service.IUserService#getByAccount(java.lang.String)
	 */
	@Override
	public User getByAccount(String name) {
		// TODO Auto-generated method stub
		return userDao.getByAccount(name);
	}
	/* (non-Javadoc)
	 * @see com.nbc.muc.service.IUserService#delete(int)
	 */
	@Override
	public int delete(int userId) {
		// TODO Auto-generated method stub
		return userDao.deleteByPrimaryKey(userId);
	}


	


}
