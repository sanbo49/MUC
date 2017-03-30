package com.nbc.muc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.nbc.muc.pojo.Syslog;
import com.nbc.muc.pojo.Unit;


public interface ISysLogDao {
    @Select("select id, operatingcontent  from syslog ")
    List<Syslog> selectAll();

	/**
	 * @param sysLog
	 */
    @Insert("insert into syslog (operatingcontent,loginName,ipAddress,methodName,methodRemark) "
    		+ "values(#{operatingcontent},#{loginName},#{ipAddress},#{methodName},#{methodRemark})")
	void save(Syslog sysLog);

}