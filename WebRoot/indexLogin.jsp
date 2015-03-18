<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	//验证码
	
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登陆页面</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="<%=basePath%>resource/css/indexLogin.css">
	
	<script type="text/javascript" src="<%=basePath%>resource/js/jquery-1.8.2.js"></script>
	<script type="text/javascript" src="<%=basePath%>resource/js/indexLogin.js"></script>
  </head>
  
  <body>
    <center>
    <!-- <form action="login.action" method="post"> -->
    	<table>
	    	<tr><td>
			用户名:</td><td><input type="text" id="username" name="username" value="${user.username }"/></td>
			</tr>
			<tr><td> 
			密码:</td><td><input type="text" id="password" name="password" value="${user.password }"/></td>
			</tr>
			<tr><td>
			输入验证码:</td><td><input type="text" name="wordcheck" id="wordcheck"/></td></tr>
			<tr>
				<td></td>
				<td>
				<img alt="change" src="<%=basePath %>wordcheck.action"onclick="changeImage(this)" style="cursor: hand"></td>
				<td>
			</tr>
			<tr><td class="errorStyle" colspan="2" id="msg"></td></tr>
			<tr ><td class="loginBtn" colspan="2">
			<input type="button" onclick="verify()" value="登陆" id="login"/>
			</td>
			</tr>
		</table>
	<!-- </form> -->
	</center>
  </body>
</html>
