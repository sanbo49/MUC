<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>DouPHP 管理中心 - 自定义导航栏 </title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <c:import url="/common" />


<script type="text/javascript">


$(function(){ 
	$(".idTabs").idTabs();
	// 自定义参数调用 
	 $("#myform").validationEngine("attach",{ 
	     promptPosition:"centerRight", 
	     scroll:false 
	 }); 
});

//ajax提交
function adduser(){
	$('#myform').ajaxSubmit({
		beforeSubmit: function(a,f,o) {
			return jQuery('#myform').validationEngine("validate");
        },
		dataType:"json",
		success:function(data){
			var success=data.success;//记录总数 
			if (success){
				alert("保存成功！");
			}else{
				$("#password").validationEngine("showPrompt","两次密码不一致","error");
				$("#confirmPassword").validationEngine("showPrompt","两次密码不一致","error");
				alert("保存失败！"+data.msg);
			}
		}
	});
	return false;
}
</script>





</head>
<body>

<div id="dcWrap">
 <div id="dcHead">
 		<c:import url="/head" /> 
</div>
<!-- dcHead 结束 -->
 <div id="dcLeft">
		<c:import url="/left" /> 
</div>
 <div id="dcMain">
   <!-- 当前位置 -->
<div id="urHere">DouPHP 管理中心<b>></b><strong>用户管理</strong> </div>   
<div class="mainBox" style="height:auto!important;height:550px;min-height:550px;">
    <h3><a href="user/list" class="actionBtn">返回列表</a>用户管理</h3>
    
    <div class="idTabs">
      <ul class="tab">
        <li><a href="#nav_add">添加用户</a></li>
      </ul>
      <div class="items">
        <div id="nav_add">
         <form action="user/add" method="post" id="myform">
          <table width="100%" border="0" cellpadding="5" cellspacing="1" class="tableBasic">
           <tr>
            <td width="80" height="35" align="right">姓名</td>
            <td>
          <input type="text" id="userName" name="userName" value="" size="40"  class="inpMain validate[required]"/>
            </td>
           </tr>
           <tr>
            <td width="80" height="35" align="right">年龄</td>
            <td>
             <input type="text" id="age" name="age" value="" size="40" class="inpMain  validate[required,custom[integer],min[0]]" />
            </td>
           </tr>

           <tr>
            <td height="35" align="right">密码</td>
            <td id="parent">
             <input type="password" name="password" id="password" value="" size="40" class="inpMain  validate[required]" />
            </td>
           </tr>
           <tr>
            <td height="35" align="right">再输一次</td>
            <td>
             <input type="password" name="confirmPassword" id="confirmPassword" value="" size="40" class="inpMain  validate[required,equals[password]]" />
            </td>
           </tr>
           <tr>
            <td></td>
            <td>
             <input type="hidden" name="token" value="30fa081a" />
             <input name="submit" class="btn" type="button" value="提交"  onclick="adduser();" />
            </td>
           </tr>
          </table>
         </form>
        </div>
        
      </div>
    </div>
 </div>
 <div class="clear"></div>
<div id="dcFooter">
  		<c:import url="/footer" /> 
</div><!-- dcFooter 结束 -->
<div class="clear"></div> </div>
</body>
</html>