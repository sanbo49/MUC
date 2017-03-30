package com.nbc.muc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.nbc.muc.pojo.Unit;


public interface IUnitDao {
    @Select("select id, unit_name  from unit ")
    List<Unit> selectAll();

}