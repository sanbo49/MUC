package com.nbc.customer.dao;

import java.util.List;

import com.nbc.customer.pojo.Customer;
import com.nbc.muc.pojo.Unit;
import com.nbc.muc.pojo.User;


public interface ICustomerDao {
    
    List<Customer> selectAll();

}