function showDiv(){
	document.getElementById("showdiv").style.display="";
}
function hiddenDiv(){
	document.getElementById("showdiv").style.display="none";
}
function changeDiv(showdiv){
	var nodediv = document.getElementById("showdiv");
	if(nodediv.style.display == "none"){
		nodediv.style.display = "block";
	}else{
		nodediv.style.display = "none";
	}
}

function backIndex(){
	var myurl = location.href;
	var endIndex = myurl.lastIndexOf('/');
	var newUrl = myurl.substring(0, endIndex);
	window.location.href=newUrl;
}