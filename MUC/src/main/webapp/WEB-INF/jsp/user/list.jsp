<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>DouPHP 管理中心 - 自定义导航栏 </title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <c:import url="/common" />
 
 
 <style>
.pager{height:25px;line-height:20px;font-size: 12px;margin: 10px 0px;text-align: center;color: #2E6AB1;overflow: hidden;}
.pager .disabled{border:1px solid #A8A8A8;color:#A8A8A8;margin:0px 5px;padding:5px 8px;text-decoration: none;}
.pager .current{border:1px solid #A8A8A8;color:#A8A8A8;margin:0px 5px;padding:5px 8px;text-decoration: none;}
.pager a{border:1px solid #9AAFE5;color:#2E6AB1;margin:0px 5px;padding:5px 8px;text-decoration: none;}
.pager a.hover,.pager a.active{background-color:#2E6AB1;border-color:#000080;color:#FFF;}
.pager a.disabled{color:#C8CDD2;cursor:auto;}
</style>

<script id="leadingOrTrailingText" type="text/x-jquery-tmpl">
	${firstName} <strong>${lastName}</strong> <br/>
</script>

<script type="text/javascript">



var page_index = 0;
var page_sizes = 10;

$(document).ready(function() {
	pageselectCallback(page_index,page_sizes);
});

	function setPage(pageIndex,pages){
		
		
		$("#Pagination").empty();
		 
	    $("#Pagination").createPage({
	        pageCount:pages,
	        current:pageIndex,
	        backFn:function(p){
	            //单击回调方法，p是当前页码
	        	pageselectCallback(p,page_sizes);
	        }
	    });
	}




	//ajax加载
	function pageselectCallback(pageIndex,page_sizes){

		var postdata = {'pageIndex': pageIndex,'pageSize':page_sizes};
		$('#myform').ajaxSubmit({
			data:postdata,
			dataType:"json",
			success:function(data){
				var pages=data.pages;//记录总数 
				var pageIndex=data.pageNo;//当前页
				setPage(pageIndex,pages);
				$( "tbody").empty();
				$( "#sometmpl" ).tmpl( data.dataList ).appendTo( "tbody" );
			}
		});
		return false;
	}
	function buttonClick(){
		pageselectCallback(page_index,page_sizes);
	}
	
</script>

<script id="sometmpl" type="text/x-jquery-tmpl">
		<tr>
			<td>{{= id}}</td>
			<td>{{= userName}}</td>
			<td align="center">{{= age}}</td>
			<td align="center"><a href="nav.php?rec=edit&id=1">编辑</a> |
				<a href="nav.php?rec=del&id=1">删除</a></td>
		</tr>
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
    <h3><a href="addnav.html" class="actionBtn">添加用户</a>用户管理</h3>
    
    <form id="myform" action="user/page" class="queryForm">
    	<input type="text" name="userName" value="" placeholder="姓名"/>
    	<input type="text" name="age" placeholder="年龄" value="" />
    	
    	
    	<a href="javascript:void(0);" onclick="buttonClick();" >go</a>
    	
    </form>
    
    
				<div class="navList">
					<table id="data_list" width="100%" border="0" cellpadding="10"
						cellspacing="0" class="tableBasic">
						<thead>
							<tr>
								<th width="113" align="center">ID</th>
								<th align="left">姓名</th>
								<th width="80" align="center">年龄</th>
								<th width="120" align="center">操作</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>

				</div>


				<div style="float:right;padding-top:10px;"  id="Pagination" class="pager"></div>
    

    
               </div>
 </div>
 <div class="clear"></div>
<div id="dcFooter">
  		<c:import url="/footer" /> 
</div><!-- dcFooter 结束 -->
<div class="clear"></div> </div>
</body>
</html>