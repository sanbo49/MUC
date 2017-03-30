<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Wopop</title>
<link href="static/login/style_log.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="static/login/style.css">
<link rel="stylesheet" type="text/css" href="static/login/userpanel.css">

<script language="javascript" type="text/javascript">  
    function   getValidateCode() {    
       document.getElementById('randomcode_img').src="static/login/validateCode.jsp?time" + new Date().getTime();
     }     
</script>  


</head>

<body class="login" mycollectionplug="bind">
	<div class="login_m">
		<div class="login_logo">
			<img src="static/login/logo.png" width="196" height="46">
		</div>
		<div class="login_boder">

			<div class="login_padding" id="login_model">
				<form id="" action="" method="post">
				<%--用于输入后台返回的验证错误信息 --%>
					<p style="color: red;"><c:out value="${authenticationErrorMessage }" /></p>
					<p>
					<div class="txt_label"> 用户名:</label><input type="text" name="username"
						class="txt_input txt_input2"
						onfocus="if (value ==&#39;Your name&#39;){value =&#39;&#39;}"
						onblur="if (value ==&#39;&#39;){value=&#39;Your name&#39;}"
						value="Your name">
					</p>
					
					<label>密码: </label><input type="password" name="password"
						class="txt_input" onfocus="if (value ==&#39;******&#39;){value =&#39;&#39;}"
						onblur="if (value ==&#39;&#39;){value=&#39;******&#39;}"
						value="******">
					<p class="forgot">
						<a id="iforget" href="javascript:void(0);">Forgot your
							password?</a>
					</p>
					
					
					
					
					 
					<label><h2>验证码：</h2><input class="txt_input randomcode" name="captcha" size="8" />
						<img id="randomcode_img" alt="" width="56" height="20"
						align='absMiddle' src="static/login/validateCode.jsp"
						onclick="getValidateCode()" /> <a
						href=javascript:getValidateCode()>刷新</a></label>

					<div class="rem_sub">
						<div class="rem_sub_l">
							<input type="checkbox" name="rememberMe"> <label
								for="checkbox">Remember me</label>
						</div>
						<label> <input type="submit" class="sub_button"
							name="button" id="button" value="SIGN-IN" style="opacity: 0.7;">
						</label>
					</div>
							
			</div>
			</form>
			<div class="login_padding">
					
						
			</div>

			<div class="copyrights">
				Collect from <a href="http://www.cssmoban.com/">企业网站模板</a>
			</div>

			<div id="forget_model" class="login_padding" style="display: none">
				<br>

				<h1>Forgot password</h1>
				<br>
				<div class="forget_model_h2">(Please enter your registered
					email below and the system will automatically reset users’ password
					and send it to user’s registered email address.)</div>
				<label> <input type="text" id="usrmail"
					class="txt_input txt_input2">
				</label>


				<div class="rem_sub">
					<div class="rem_sub_l"></div>
					<label> <input type="submit" class="sub_buttons"
						name="button" id="Retrievenow" value="Retrieve now"
						style="opacity: 0.7;"> <input type="submit"
						class="sub_button" name="button" id="denglou" value="Return"
						style="opacity: 0.7;">

					</label>
				</div>
			</div>






			<!--login_padding  Sign up end-->
		</div>
		<!--login_boder end-->
	</div>
	<!--login_m end-->
	<br>
	<br>
	<p align="center">
		More Templates <a href="http://www.cssmoban.com/" target="_blank"
			title="模板之家">模板之家</a> - Collect from <a
			href="http://www.cssmoban.com/" title="MUC" target="_blank">MUC</a>
	</p>



</body>
</html>