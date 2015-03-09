<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>合并文件成功</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="<%=basePath %>resource/css/canRename.css">

  </head>
  
  <body>
  <div>
   	合并写入完毕
   	<br/>
   	文件位置:<c:out value="${localtion}"></c:out>
   </div>
   
   <span id="time" class="numStyle">6</span>秒种后自动跳转,如果不跳转,请点击下面链接 
<script language="JavaScript1.2" type="text/javascript">

//  Place this in the 'head' section of your page.  

function delayURL(url) {
	var delay = document.getElementById("time").innerHTML;
	if(delay>0){
		delay--;
		document.getElementById("time").innerHTML = delay;
	}else{
		window.top.location.href=url;
	}
	setTimeout("delayURL('"+url+"')",1000);	
    
}

//-->

</script>

<a href="<%=basePath%>">返回首页</a>
<script type="text/javascript">
	delayURL("input.action");
</script>
  </body>
</html>
