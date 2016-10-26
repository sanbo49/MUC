package com.nbc.customer.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.nbc.customer.dao.ICustomerDao;
import com.nbc.customer.pojo.Customer;
import com.nbc.customer.service.ICustomerService;

@Service("customerService")
public class CustomerServiceImpl implements ICustomerService {
	@Resource
	private ICustomerDao customerDao;

	public List<Customer> getAll(int pageNumber,  
            int pageSize) {
		// TODO Auto-generated method stub
		 PageHelper.startPage(pageNumber,pageSize);  
		 return this.customerDao.selectAll();
		 
	}


	


}
