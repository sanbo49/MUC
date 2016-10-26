package com.nbc.muc.dao;

import java.util.List;

import com.nbc.muc.pojo.Unit;
import com.nbc.muc.pojo.User;


public interface IUnitDao {
    
    List<Unit> selectAll();

}