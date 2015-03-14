<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>搜索文件</title>
<link rel="stylesheet" type="text/css" href="<%=basePath %>resource/css/index.css">
<script type="text/javascript" src="<%=basePath%>resource/js/index.js"></script>
</head>
<body>
<div class="left">

	<div class="divTurnoff" onclick="changeDiv('turnoffpc')">关机</div>
	<div style=";">${turnOffSuccess }
	</div>
	<div id="turnoffpc" style="display:none;">
		<form action="turnOff.action" method="post" onsubmit="return trunOffPC()">
			<input type="hidden" id="turnoffID" name="turnoffTime"/>
			<table>
				<tr><td>
			<input type="radio" name="turnoff" value="0.02">立即关机</td>
				</tr>
				<tr><td>
			<input type="radio" name="turnoff" value="2">2分钟后关机</td>
				</tr>
				<tr><td>
			<input type="radio" name="turnoff" value="5">5分钟后关机</td>
				</tr>
				<tr><td>
			<input type="radio" name="turnoff" value="10">10分钟后关机</td>
				</tr>
				<tr><td>
			<input type="radio" name="turnoff" value="30">半小时后关机</td>
				</tr>
				<tr><td>
			<input type="radio" name="turnoff" value="60">1小时后关机</td>
				</tr>
				<tr><td>
			<input type="radio" name="turnoff" value="120">2小时后关机</td>
				</tr>
				<tr><td>
			<input type="radio" name="turnoff" value="180">3小时后关机</td>
				</tr>
				
				<tr><td>
			<input type="radio" name="turnoff" value="720">12小时后关机</td>
				</tr>
				
				
			<TR >
			<c:if test="${turnOffSuccess!=null }">
				<td class="cancelOff"><input type="radio" name="turnoff" value="-1" >取消关机</td>
			</c:if>
			</TR>
				<tr><td id="errormsg"></td></tr>
				<tr><td>
			<input type="submit" value="提交"/></td>
				</tr>
			</table>
		</form>
	</div>

</div>	
<!-- end left -->
	
<div class="middle">
	<div>
		<p>
		${fullStr }
		</p>
		<c:if test="${fullStr==null }">
		
	<center>
	<h2 class="mycenter">搜索文件</h2>
		<form action="search.action" method="post"  onsubmit="return validate_form(this)">
			<table>
			<tr>
				<td>
			指定盘符:
				</td>
				<td>
			<input type="text" name="diskname"/>
				</td>
				<td id="errordisk"></td>
			</tr>
			<tr>
				<td>
			文件夹名:
				</td>
				<td>
			<input type="text" name="foldername"/>
				</td>
				<td></td>
			</tr>
			<tr>
				<td>
			文件名:
				</td>
				<td>
			<input type="text" name="filename"/>
				</td><td></td>
			</tr>
			<tr>
				<td>
			文件后缀:
				</td>
				<td>
			<input type="text" name="suffix"/>
				</td><td></td>
			</tr>
			<tr>
				<td></td>
				<td>
			<input type="submit" value="提交"/>
				</td><td></td>
			</tr>
			</table>
		</form>
		</center>
		
		</c:if>
		
	</div>
</div>	
<!-- end middle -->
	
</body>
</html>