/**
 * 
 */
package com.nbc.muc.service;


import java.lang.reflect.Method;  
import java.text.SimpleDateFormat;  
import java.util.Calendar;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;  
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Component;  
import org.springframework.web.context.request.RequestContextHolder;  
import org.springframework.web.context.request.ServletRequestAttributes;

import com.nbc.common.StringUtils;
import com.nbc.common.TCPIPUtil;
import com.nbc.common.log.MethodLog;
import com.nbc.muc.dao.ISysLogDao;
import com.nbc.muc.pojo.Syslog;
  

  
/** 
 * \ 
 *  
 * @Aspect 实现spring aop 切面（Aspect）： 
 *         一个关注点的模块化，这个关注点可能会横切多个对象。事务管理是J2EE应用中一个关于横切关注点的很好的例子。 在Spring 
 *         AOP中，切面可以使用通用类（基于模式的风格） 或者在普通类中以 @Aspect 注解（@AspectJ风格）来实现。 
 *  
 *         AOP代理（AOP Proxy）： AOP框架创建的对象，用来实现切面契约（aspect contract）（包括通知方法执行等功能）。 
 *         在Spring中，AOP代理可以是JDK动态代理或者CGLIB代理。 注意：Spring 
 *         2.0最新引入的基于模式（schema-based 
 *         ）风格和@AspectJ注解风格的切面声明，对于使用这些风格的用户来说，代理的创建是透明的。 
 * @author q 
 *  
 */  
@Component  
@Aspect  
public class LogService {  
	private Logger logger = Logger.getLogger(this.getClass());  
    @Resource  
    private ISysLogDao syslogDao;  
  
    public LogService() {  
        logger.info("Aop LogService");  
    }  
    /** 
     * 在Spring 
     * 2.0中，Pointcut的定义包括两个部分：Pointcut表示式(expression)和Pointcut签名(signature 
     * )。让我们先看看execution表示式的格式： 
     * 括号中各个pattern分别表示修饰符匹配（modifier-pattern?）、返回值匹配（ret 
     * -type-pattern）、类路径匹配（declaring 
     * -type-pattern?）、方法名匹配（name-pattern）、参数匹配（(param 
     * -pattern)）、异常类型匹配（throws-pattern?），其中后面跟着“?”的是可选项。 
     *  
     * @param point 
     * @throws Throwable 
     */  
  
////    // 这个是测试
//    @AfterReturning("execution(* com.nbc.muc.controller..*.*(..))")
//    public void controllerAspect() {
//    	System.out.println("controllerAspect"+" UserController"); 
//    }
//    
 // 这里是关键点，把切面的连接点放在了我们的注解上
    @Pointcut("@annotation(com.nbc.common.log.MethodLog)")
    public void pointcut() {
//    	logger.info("定义");
    }
    

    
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {  
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder  
                .getRequestAttributes()).getRequest();  
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E");  
        Calendar ca = Calendar.getInstance();  
        String operDate = df.format(ca.getTime());
        String ip = TCPIPUtil.getIpAddr(request);
        String loginName=  (String) SecurityUtils.getSubject().getPrincipal();  
        
        Object oProceed=point.proceed();
        logger.info("返回值："+oProceed.toString());
        if (loginName == null) {  
            loginName = "匿名用户";  
        }  
        
        
        
        MethodLog methodLog= getMethodLog(point);
        String methodRemark = methodLog!=null?methodLog.remark():"";  
        String functionName =methodLog!=null?methodLog.createHisJsonFunction():""; 
        String methodName = point.getSignature().getName();  
        String packages = point.getThis().getClass().getName();  
        if (packages.indexOf("$$EnhancerByCGLIB$$") > -1 || packages.indexOf("$$EnhancerBySpringCGLIB$$") > -1) { // 如果是CGLIB动态生成的类  
            try {  
                packages = packages.substring(0, packages.indexOf("$$"));  
            } catch (Exception ex) {  
                ex.printStackTrace();  
            }  
        }  
  
        //获取处理函数的参数及处理结果
        Object[] method_param = null;  
        Object object;  
        try {  
            method_param = point.getArgs(); //获取方法参数   
            object = point.proceed();  
        } catch (Exception e) {  
            // 异常处理记录日志..log.error(e);  
            throw e;  
        }  
        
        //通过反射得到整理json的函数，被执行
        String Operatingcontent="";
        Class<?> clz=point.getThis().getClass();
        Object o=clz.newInstance();
        try {
	        Method m = clz.getMethod(functionName,String.class, Object[].class,Object.class);
	        if (m!=null)
	        {
	        	Object result =m.invoke(o,methodName,method_param,object);
	        	Operatingcontent=result.toString();
	        }
        }catch(Exception e){
        	Operatingcontent="处理函数不存在！";
        	
        }
        
        
        
        
        
        Syslog sysLog = new Syslog();  
        sysLog.setIpAddress(ip);  
        sysLog.setLoginName(loginName);  
        sysLog.setMethodName(packages + "." + methodName);  
        sysLog.setMethodRemark(methodRemark);  
        Operatingcontent=Operatingcontent.length()>200?Operatingcontent.substring(0,200):Operatingcontent;
        sysLog.setOperatingcontent(Operatingcontent); 
        syslogDao.save(sysLog);  
        return object;  
    }  
  
//    // 方法运行出现异常时调用    
//     @AfterThrowing(pointcut = "execution(* com.nbc.muc.controller.*(..))",  
//     throwing = "ex")  
//    public void afterThrowing(Exception ex) {  
//        System.out.println("afterThrowing");  
//        System.out.println(ex);  
//    }  
  
    // 获取方法的中文备注____用于记录用户的操作日志描述  
    public static MethodLog getMethodLog(ProceedingJoinPoint joinPoint)  
            throws Exception {  
        String targetName = joinPoint.getTarget().getClass().getName();  
        String methodName = joinPoint.getSignature().getName();  
        Object[] arguments = joinPoint.getArgs();  
  
        Class targetClass = Class.forName(targetName);  
        Method[] method = targetClass.getMethods();  
        MethodLog methodCache = null;  
        for (Method m : method) {  
            if (m.getName().equals(methodName)) {  
                Class[] tmpCs = m.getParameterTypes();  
                if (tmpCs.length == arguments.length) {  
                    methodCache = m.getAnnotation(MethodLog.class);  
                    break;  
                }  
            }  
        }  
        return methodCache;  
    }  
  

}  