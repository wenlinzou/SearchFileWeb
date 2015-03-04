<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>搜索结果</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="<%=basePath %>resource/css/success1.css">
	<link rel="syylesheet" type="text/css" href="<%=basePath%>resource/css/top.css">
	<script type="text/javascript" src="<%=basePath%>resource/js/top.js"></script> 
	<script type="text/javascript" src="<%=basePath%>resource/js/success1.js"></script>
  </head>
  
  <body>
  <div id="showdiv" style="display: none">
  	<form action="print.action" method="post">
  		
  		<input type="hidden" value="${fileLists }" id="showdiv"/>
  		<table>
  		<tr class="mycenter"><td colspan="2"></td>
  		</tr>
  		
		<tr><td>盘符:</td><td><input type="text" name="diskname"/></td>
  		</tr>
  		<tr>
  			<td>文件名称:</td>
  			<td><input type="text" name="filename"/></td>
  		</tr>
  		<tr>
  			<td></td>
  			<td><input type="submit" value="写入进本地磁盘" id="showthis"/></td>
  		</tr>
  		</table>
  	</form>	
  </div>
 
  <input type="button" value="显示" onclick="showDiv()"/>
  <input type="button" value="隐藏" onclick="hiddenDiv()"/>
  <input type="button" value="隐显" onclick="changeDiv('showdiv')"/>
  <input type="button" value="回到首页" onclick="backIndex()"/>	
  
  	<div id="mySearchInfo">
	    <table>
	    	<tr><td class="mytitle">搜索结果-文件信息</td></tr>
	    	
	    	<c:forEach items="${fileLists }" var="file" varStatus="status">
	    		<tr <c:if test="${status.index%2!=0}">class="mytr"</c:if>>
	    			<td>
	    				<a href="playServlet.action?myUrl=${file}">
	    					<c:if test="${fileLists!=null }">${file }</c:if>
	    				</a>
	    			</td>
	    		</tr>
	    	</c:forEach>
	    </table>
   	</div>
    
    <div class="myright"><a href="#" onclick="gotoTop();return false;" class="myright"></a></div>
    
  </body>
</html>
