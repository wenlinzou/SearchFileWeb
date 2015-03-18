var totalCount = 5;
function verify() {
	
    //var userName = $("#userName").val();
    //$.get("AJAXServer?name="+userName);
    //1获取文本框中的内容//document.getElementById("id");
    //JQuery的查找节点的方式，参数中#加上属性值可以找到一个节点
    //JQuery的方法返回的都是JQuery对象，可以继续在上面执行其他的jquery方法
   // var jqueryObj = $("username");
    //获取节点的值
    //var userName = jqueryObj[0].val();
    
    var username = $("input[name='username']").val(); 
    var password = $("input[name='password']").val(); 
    var wordcheck = $("input[name='wordcheck']").val(); 

    //2将文本框中的数据发送给服务器的servlet
    //在javascript当中一个简单的对象的定义方法
    var obj = {username:username, password: password, wordcheck:wordcheck};

    //使用JQuery的XMLHTTPRequest对象的封装

    $.ajax({
        type: "POST",              //http请求方式
        url: "login.action",    //服务器端url地址
//        data: "username=" + username+"&password="+password,    //发送给服务器短的数据
        data: obj,
        dataType: "xml",           //告诉Jquery返回的数据方式
        success: callback,             //定义交互完成，并且服务器正确返回数据时调用的回调函数
        error:errorfun
    });


}
function errorfun(){
	$("#msg").html("后台自杀了!!提交失败...");
}
//回调函数
var count = 0;
function callback(data) {
	count++;
	if(count>totalCount){
		$("#msg").html("最多输入"+totalCount+"次,已经超出!");
//		$("#login").css("disable","disable");
		$("#login").attr({"disabled":true});

		return;
	}
	
    //3接受服务器端返回的数据
    //需要将data这个dom对象中的数据解析出来
    //首先需要将dom对象转换成Jquery对象
    var jqueryObj = $(data);
    //获取message节点
    var message = jqueryObj.children();
    //获取文本内容
    var text = message.text();
    //4将服务器端返回的数据动态的显示在页面上
    //找到保存结果信息的节点
    var msgObj = $("#msg");

    if(!goToOther(text)){
    //动态的改变页面中div节点中的内容
    	msgObj.html(text);
	}
}
function goToOther(msgObj){
	if(msgObj=="canLogin"){
		var myurl = location.host;
//		myurl+"/input.action";
		//http://localhost:8080/SearchFileWeb/input.action
		window.location.href = "http://"+myurl+"/SearchFileWeb/input.action";
		return true;
	}else{
		return false;
	}
}

//验证码
function changeImage(img) {
	img.src = img.src + "?" + new Date().getDate();
}
