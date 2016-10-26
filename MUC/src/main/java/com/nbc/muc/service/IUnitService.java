package com.nbc.muc.service;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.nbc.muc.pojo.Unit;
import com.nbc.muc.pojo.User;

public interface IUnitService {


	public List<Unit> getUnitAll(int pageNumber,  
            int pageSize);
}
