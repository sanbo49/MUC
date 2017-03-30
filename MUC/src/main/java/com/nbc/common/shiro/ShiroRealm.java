/**
 * 
 */
package com.nbc.common.shiro;


import java.util.ArrayList;  
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;  
import org.apache.shiro.authc.AuthenticationInfo;  
import org.apache.shiro.authc.AuthenticationToken;  
import org.apache.shiro.authc.SimpleAuthenticationInfo;  
import org.apache.shiro.authz.AuthorizationException;  
import org.apache.shiro.authz.AuthorizationInfo;  
import org.apache.shiro.authz.SimpleAuthorizationInfo;  
import org.apache.shiro.realm.AuthorizingRealm;  
import org.apache.shiro.subject.PrincipalCollection;

import com.nbc.muc.pojo.User;
import com.nbc.muc.service.IUserService;
 
 
/** 
* @author chenlf 
*  
*         2014-3-24 
*/  
public class ShiroRealm extends AuthorizingRealm {  
	@Resource
	private IUserService userService;
   /* 
    * (non-Javadoc) 
    *  
    * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection) 
    */  
   @Override  
   protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {  
       // 根据用户配置用户与权限  
       if (principals == null) {  
           throw new AuthorizationException("PrincipalCollection method argument cannot be null.");  
       }  
       String name = (String) getAvailablePrincipal(principals);  
       List<String> roles = new ArrayList<String>();  
       // 简单默认一个用户与角色，实际项目应User user = userService.getByAccount(name);  
       User user = userService.getByAccount(name);  
//       User user = new User("admin", "123456");  
//       Role role = new Role("member");  
//       user.setRole(role);  
       if (user.getUserName().equals(name)) {  
//           if (user.getRole() != null) {  
//               roles.add(user.getRole().getName());  
//           }  
       } else {  
           throw new AuthorizationException();  
       }  
       SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();  
       // 增加角色  
       info.addRoles(roles);  
       return info;  
   }  
 
   /* 
    * (non-Javadoc) 
    *  
    * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken) 
    */  
   @Override  
   protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)  
           throws AuthenticationException {  
	   CaptchaUsernamePasswordToken token = (CaptchaUsernamePasswordToken) authcToken;  
//      过滤器已经检查过了，这里不需要再检查验证码了
//	   String parm = token.getCaptcha();
//		
//		if (parm != null && !"1111".equals(parm.toLowerCase())) {//忽略大小写。
//			throw new IncorrectCaptchaException("验证码错误！");
//		}
       
       // 简单默认一个用户,实际项目应User user = userService.getByAccount(token.getUsername());  
//       User user = new User("admin", "123456");  
		String name =token.getUsername();  
       User user = userService.getByAccount(name);  
       if (user == null) {  
           throw new AuthorizationException();  
       }  
       SimpleAuthenticationInfo info = null;  
       if (user.getUserName().equals(token.getUsername())) {  
           info = new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(), getName());  
       }  
       return info;  
   }  
}  