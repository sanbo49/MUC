package com.nbc.muc.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nbc.common.BaseController;
import com.nbc.common.BeanUtil;
import com.nbc.common.IBaseController;
import com.nbc.common.PagedResult;
import com.nbc.muc.pojo.User;
import com.nbc.muc.service.IUserService;


@Controller
@RequestMapping("/user")
public class UserController extends BaseController implements IBaseController {
	@Resource
	private IUserService userService;
	
	private static Logger log=Logger.getLogger(UserController.class);
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request,Model model){
//		PagedResult<User> pageResult= this.userService.getUserAll(2,10);
//		return responseSuccess(pageResult);
		return "user/list";
	}
	@RequestMapping("/page")
	public void page(HttpSession session,HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		User user = new User(); 
		BeanUtils.populate(user,request.getParameterMap());
		log.info(user);
		String sql=createSql(user);
		
		Integer pageNumber= 1;
		if (request.getParameter("pageIndex")!=null){
			pageNumber=Integer.parseInt(request.getParameter("pageIndex").toString());
		}
		
		Integer pageSize=10;
		if (request.getParameter("pageSize")!=null){
			pageSize=Integer.parseInt(request.getParameter("pageSize").toString());
		}
		try {
			PagedResult<User> pageResult = this.userService.queryUsers(pageNumber, pageSize, sql);
			  response.setCharacterEncoding("utf-8");
			  response.getWriter().write(responseSuccess(pageResult));
			  response.getWriter().flush();
			
		} catch (Exception e) {
			responseFail(e.getMessage());
		}
		//return "user/list";
	}
	

	public <T> String createSql(T t) {
		// TODO Auto-generated method stub
		StringBuffer sb=new StringBuffer();
		sb.append("select * from user_t where 1=1 ");
		if (t instanceof User){
			User user=(User)t;
			if (user.getUserName()!=null){
				sb.append(" and user_name like '%"+user.getUserName()+"%'");
			}
		}
		return sb.toString();
	}
}
