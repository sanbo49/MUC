package com.nbc.customer.service;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.nbc.customer.pojo.Customer;
import com.nbc.muc.pojo.Unit;
import com.nbc.muc.pojo.User;

public interface ICustomerService {


	public List<Customer> getAll(int pageNumber,  
            int pageSize);
}
