package com.nbc.muc.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nbc.common.BaseController;
import com.nbc.common.BeanUtil;
import com.nbc.common.IBaseController;
import com.nbc.common.PagedResult;
import com.nbc.common.RedisService;
import com.nbc.muc.pojo.User;
import com.nbc.muc.service.IUserService;


@Controller
@RequestMapping("/user")
public class UserController extends BaseController implements IBaseController {
	@Resource
	private IUserService userService;
	 @Autowired
	 private UserValidation userValidation; // 用户自定义验证
	
	private static Logger log=Logger.getLogger(UserController.class);
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request,Model model){
		return "user/list";
	}
	
	@RequestMapping("/foradd")
	public String foradd(HttpServletRequest request,Model model){
		return "user/add";
	}
	
	@RequestMapping("/add")
	public String add(@Valid @ModelAttribute User user,BindingResult result,Model model, HttpServletResponse response,Errors errors){
		
		userValidation.validateAdd(user, result);
		JSONObject jsonObject=new JSONObject();
		if (result.hasErrors()) {
			jsonObject.put("success", false);
			jsonObject.put("msg", result.getFieldError().getDefaultMessage());
		} else {
			int i=this.userService.insert(user);
			log.info("return value:"+i);
			jsonObject.put("success", true);
		}

		try {
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(jsonObject.toString());
			response.getWriter().flush();
		}catch(Exception e) {
			log.info("error");
		}
		return null;
	}
	
	@RequestMapping("/forupdate")
	public String forupdate(@RequestParam("userid")  final Integer userId,Model model){
		User user=this.userService.getUserById(userId);
		model.addAttribute(user);
		return "user/update";
	}
	
	@RequestMapping("/update")
	public String update(@Valid @ModelAttribute User user,BindingResult result,Model model, HttpServletResponse response,Errors errors){
		
		userValidation.validateUpdate(user, result);
		JSONObject jsonObject=new JSONObject();
		if (result.hasErrors()) {
			jsonObject.put("success", false);
			jsonObject.put("msg", result.getFieldError().getDefaultMessage());
		} else {
			int i=this.userService.update(user);
			log.info("return value:"+i);
			jsonObject.put("success", true);
		}

		try {
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(jsonObject.toString());
			response.getWriter().flush();
		}catch(Exception e) {
			log.info("error");
		}
		return null;
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
			//PagedResult<User> pageResult = this.userService.queryUsers(pageNumber, pageSize, sql);
			
			PagedResult<User> pageResult = this.userService.getUserAll(pageNumber, pageSize);
			
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
