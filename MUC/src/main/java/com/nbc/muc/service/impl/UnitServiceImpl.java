package com.nbc.muc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.nbc.muc.dao.IUnitDao;
import com.nbc.muc.dao.IUserDao;
import com.nbc.muc.pojo.Unit;
import com.nbc.muc.pojo.User;
import com.nbc.muc.service.IUnitService;
import com.nbc.muc.service.IUserService;

@Service("unitService")
public class UnitServiceImpl implements IUnitService {
	@Resource
	private IUnitDao unitDao;

	public List<Unit> getUnitAll(int pageNumber,  
            int pageSize) {
		// TODO Auto-generated method stub
		 PageHelper.startPage(pageNumber,pageSize);  
		 return this.unitDao.selectAll();
		 
	}


	


}
