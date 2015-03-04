<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <%-- <base href="<%=basePath%>"> --%>
    
    <title>播放页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	<%
  		String playpath = request.getParameter("playUrl");
  	String iplay = (String)session.getAttribute("iPlay");
  	%>
  	
	<input type="hidden" value="<%=iplay%>" id="playinfos"/>
    <div class="hiddenplay">
	    <input type="file" id="file" onchange="onInputFileChange()">  
		<video id="audio_id" controls autoplay loop>Your browser can't support HTML5 Audio</video>  
	</div>
	
	<div>
		<%-- <input type="button"  onclick="playMyVideo()" value="播放"/>
		<input type="file" id="playinfo" src="<%=iplay%>"/>
		<video id="paly_id" controls autoplay loop>Your browser can't support HTML5 Audio</video>   --%>
	</div>
	
	<script>  
		var count = 1; 
		window.onload=function(){
			count++;
			var nodediv = document.getElementById("audio_id");
			nodediv.style.display = "none";
		}
		function onInputFileChange() {
			if(count>1){
				var nodediv = document.getElementById("audio_id");
				nodediv.style.display = "block";
			}
			var file = document.getElementById('file').files[0];
			var url = URL.createObjectURL(file);  
			console.log(url);
			document.getElementById("audio_id").src = url;
		}
		
		
		function playMyVideo(){
			var file = document.getElementById('playinfos');
			alert(file);
			var url = URL.createObjectURL(file.files[0]);
			console.log(url);
			document.getElementById("paly_id").src=url;
		}
		
	</script> 
	
	<hr />
	WTF:<%=playpath %>
  	<%=iplay %>
  	
   
  </body>
</html>
