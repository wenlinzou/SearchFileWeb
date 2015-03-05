function showDiv() {
	document.getElementById("showdiv").style.display = "";
}
function hiddenDiv() {
	document.getElementById("showdiv").style.display = "none";
}

// 切换显示和隐藏
function changeDiv(showdiv) {
	var nodediv = document.getElementById(showdiv);
	if (nodediv.style.display == "none") {
		nodediv.style.display = "block";
	} else {
		nodediv.style.display = "none";
	}
}

// 返回首页
function backIndex() {
	var myurl = location.href;
	var endIndex = myurl.lastIndexOf('/');
	var newUrl = myurl.substring(0, endIndex);
	window.location.href = newUrl;
}

// 全选
function selectAll(node) {
	var filelistNodes = document.getElementsByName("filePathList");
	for (var i = 0; i < filelistNodes.length; i++) {
		filelistNodes[i].checked = node.checked;
	}
}

/* 修改字体 */
function changeFont(selectName) {
	var oNewsText = document.getElementById("mySearchInfo");
	oNewsText.className = selectName;
	/*
	 * var trNodes = document.getElementsByName("filePathList"); for(var i = 0;
	 * i < trNodes.length; i++){
	 * 
	 * trNodes[i].className = selectName; }
	 */
}
function validate_file(field, alerttxt, selectName) {
	with (field) {
		if (value == null || value == "") {
			$("errorfile").innerHTML = alerttxt;
			$("errorfile").className = selectName;
			return false;
		} else {
			return true;
		}
	}
}
function $(val){
	return document.getElementById(val);
}
function validate_disk(diskname, alerttxt, selectName) {
	with (diskname) {
		if (value == null || value == "") {
			$("errordisk").innerHTML = alerttxt;
			$("errordisk").className = selectName;
			return false;
		} else {
			return true;
		}
	}
}
function validate_form(thisform) {
	with (thisform) {
		var diskwarn = "请输入盘符名称";
		var filewarn = "请输入文件名称";
		if((validate_disk(diskname,diskwarn,"errordiskfile")==false) && 
				(validate_file(filename,filewarn,"errordiskfile")==false)){
			diskname.focus();
			return false;
		}
		if(validate_disk(diskname,diskwarn)==false){
			diskname.focus();
			return false;
		}
		if (validate_file(filename, filewarn) == false) {
			filename.focus();
			return false;
		}
		
	}
}