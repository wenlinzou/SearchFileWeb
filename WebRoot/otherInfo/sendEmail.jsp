<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>发送邮件</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css" href="<%=basePath %>resource/css/sendEmail.css">
</head>

<body>
	<form action="sendemail.html" method="post">
		<table>
			<tr>
				<td class="tdwordRight">发件邮箱</td><td><input type="text" name="username" class="inputStyle"/></td>
			</tr>
			<tr>
				<td class="tdwordRight">邮箱密码</td><td><input type="password" name="password" class="inputStyle"/></td>
			</tr>
			<tr>
				<td class="tdwordRight">收邮人</td><td><input type="text" name="sendToEmailName" class="inputStyle"/></td>
			</tr>
			<tr>
				<td class="tdwordRight">标题</td><td><input type="text" name="subject" class="inputStyle"/></td>
			</tr>
			<tr>
				<td class="tdwordRight">内容</td><td>
				<!-- <input type="text" name="content"/> -->
				<textarea rows="10px" cols="30px" name="content" class="inputStyle"></textarea>
				</td>
			</tr>
			<tr>
				<td> </td><td><input type="submit" value="发送"/></td>
			</tr>
		</table>
	</form>
	<br>
</body>
</html>
