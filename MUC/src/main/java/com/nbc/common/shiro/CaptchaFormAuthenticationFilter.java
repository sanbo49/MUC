/**
 * 
 */
package com.nbc.common.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import com.nbc.common.StringUtils;
 
/**
 * 
 * @author <a href="mailto:ketayao@gmail.com">ketayao</a> 
 * Version 1.1.0
 * @since 2012-8-7 上午9:20:26
 */
 
public class CaptchaFormAuthenticationFilter extends FormAuthenticationFilter {
 
    private String captchaParam = "captcha";
 
    public String getCaptchaParam() {
        return captchaParam;
    }
 
    protected String getCaptcha(ServletRequest request) {
        return WebUtils.getCleanParam(request, getCaptchaParam());
    }
 
    @Override
    protected AuthenticationToken createToken(ServletRequest request,
            ServletResponse response) {
        String username = getUsername(request);
        String password = getPassword(request);
        String captcha = getCaptcha(request);
        boolean rememberMe = isRememberMe(request);
        String host = getHost(request);
        return new CaptchaUsernamePasswordToken(username, password, rememberMe,
                host, captcha);
    }
    
    
	@Override
	protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request,
			ServletResponse response) {
		boolean result = super.onLoginFailure(token, e, request, response);
		String errorMessage = "用户名或者密码错误";
		 if (e instanceof IncorrectCaptchaException) {
			 errorMessage = "验证码输入错误";
		 }
		 else if (e instanceof IncorrectCredentialsException) {  
	        errorMessage = "登录密码错误";  
	    } else if (e instanceof ExcessiveAttemptsException) {  
	    	errorMessage = "登录失败次数过多";  
	    } else if (e instanceof LockedAccountException) {  
	    	errorMessage = "帐号已被锁定";  
	    } else if (e instanceof DisabledAccountException) {  
	    	errorMessage = "帐号已被禁用";  
	    } else if (e instanceof ExpiredCredentialsException) {  
	    	errorMessage = "帐号已过期";  
	    } else if (e instanceof UnknownAccountException) {  
	    	errorMessage = "帐号不存在";  
	    } 
	    
//	    else if (e instanceof UnauthorizedException) {  
//	    	errorMessage = "您没有得到相应的授权！" + e.getMessage();  
//		
		
		
		
		
		
		
		
		
		request.setAttribute("authenticationErrorMessage", errorMessage);
		return result;
	}
    
    
    @Override  
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {  
        // 在这里进行验证码的校验  (先注释，不管检验码)
//        HttpServletRequest httpServletRequest = (HttpServletRequest) request;  
//        HttpSession session = httpServletRequest.getSession();  
//  
//        // 取出验证码  
//        String validateCode = getCaptcha(request);
//        // 取出页面的验证码  
//        // 输入的验证和session中的验证进行对比  
//        String randomcode =StringUtils.toEmpty(session.getAttribute(captchaParam));
//        if (randomcode != null && validateCode != null && !randomcode.toUpperCase().equals(validateCode.toUpperCase())) {  
//            // 如果校验失败，将验证码错误失败信息，通过shiroLoginFailure设置到request中  
//            httpServletRequest.setAttribute("authenticationErrorMessage", "验证码输入错误");  
//            // 拒绝访问，不再校验账号和密码  
//            return true;  
//        }  
        return super.onAccessDenied(request, response);  
    }  
    
    
 
    /**
     * 覆盖默认实现，用sendRedirect直接跳出框架，以免造成js框架重复加载js出错。
     * @param token
     * @param subject
     * @param request
     * @param response
     * @return
     * @throws Exception  
     * @see org.apache.shiro.web.filter.authc.FormAuthenticationFilter#onLoginSuccess(org.apache.shiro.authc.AuthenticationToken, org.apache.shiro.subject.Subject, javax.servlet.ServletRequest, javax.servlet.ServletResponse)
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject,
            ServletRequest request, ServletResponse response) throws Exception {
        //issueSuccessRedirect(request, response);
        //we handled the success redirect directly, prevent the chain from continuing:
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;
         
        if (!"XMLHttpRequest".equalsIgnoreCase(httpServletRequest.getHeader("X-Requested-With")) 
                || request.getParameter("ajax") == null) {// 不是ajax请求
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + this.getSuccessUrl());
        } else {
            httpServletRequest.getRequestDispatcher("/user/list").forward(httpServletRequest, httpServletResponse);
        }
         
        return false;
    }
}