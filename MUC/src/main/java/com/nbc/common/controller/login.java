/**
 * 
 */
package com.nbc.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nbc.common.BaseController;

/*** <p>Title: </p>
* <p>Description: </p>
* <p>Company: </p> 
* @author 
* @date 
*/
@Controller
public class login  extends BaseController{
	@RequestMapping(value = "login")  
	public String login(HttpServletRequest request) {
		return "login";
	}
//	@RequestMapping(value = "dologin")  
//	public String doLogin(HttpServletRequest request, Model model) {  
//	    String msg = "";  
//	    String userName = request.getParameter("username");  
//	    String password = request.getParameter("password");  
//	    System.out.println(userName);  
//	    System.out.println(password);  
//	    UsernamePasswordToken token = new UsernamePasswordToken(userName, password);  
//	    token.setRememberMe(true);  
//	    Subject subject = SecurityUtils.getSubject();  
//	    try {  
//	        subject.login(token);  
//	        if (subject.isAuthenticated()) {  
//	            return "redirect:/user/list";  
////	        	return onLoginSuccess(token, subject, request, response);
//	        } else {  
//	            return "login";  
//	        }  
//	    } catch (IncorrectCredentialsException e) {  
//	        msg = "登录密码错误. Password for account " + token.getPrincipal() + " was incorrect.";  
//	        model.addAttribute("message", msg);  
//	        System.out.println(msg);  
//	    } catch (ExcessiveAttemptsException e) {  
//	        msg = "登录失败次数过多";  
//	        model.addAttribute("message", msg);  
//	        System.out.println(msg);  
//	    } catch (LockedAccountException e) {  
//	        msg = "帐号已被锁定. The account for username " + token.getPrincipal() + " was locked.";  
//	        model.addAttribute("message", msg);  
//	        System.out.println(msg);  
//	    } catch (DisabledAccountException e) {  
//	        msg = "帐号已被禁用. The account for username " + token.getPrincipal() + " was disabled.";  
//	        model.addAttribute("message", msg);  
//	        System.out.println(msg);  
//	    } catch (ExpiredCredentialsException e) {  
//	        msg = "帐号已过期. the account for username " + token.getPrincipal() + "  was expired.";  
//	        model.addAttribute("message", msg);  
//	        System.out.println(msg);  
//	    } catch (UnknownAccountException e) {  
//	        msg = "帐号不存在. There is no user with username of " + token.getPrincipal();  
//	        model.addAttribute("message", msg);  
//	        System.out.println(msg);  
//	    } catch (UnauthorizedException e) {  
//	        msg = "您没有得到相应的授权！" + e.getMessage();  
//	        model.addAttribute("message", msg);  
//	        System.out.println(msg);  
//	    }  
//	    return "login";  
//	}  
	
	
//	@RequestMapping(value = "error")
//	@ResponseBody
//	public String error(HttpServletRequest request, Model model) {  
////		Subject subject = SecurityUtils.getSubject();  
////		subject.logout();
//		return "用户名密码不正确";
//	}
	
//	@RequestMapping(value = "logout")  
//	public String logout(HttpServletRequest request, Model model) {  
//		Subject subject = SecurityUtils.getSubject();  
//		subject.logout();
//		return null;
//	}
}
