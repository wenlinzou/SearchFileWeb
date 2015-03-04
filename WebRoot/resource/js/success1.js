function showDiv() {
	document.getElementById("showdiv").style.display = "";
}
function hiddenDiv() {
	document.getElementById("showdiv").style.display = "none";
}
function changeDiv(showdiv) {
	var nodediv = document.getElementById("showdiv");
	if (nodediv.style.display == "none") {
		nodediv.style.display = "block";
	} else {
		nodediv.style.display = "none";
	}
}

function backIndex() {
	var myurl = location.href;
	var endIndex = myurl.lastIndexOf('/');
	var newUrl = myurl.substring(0, endIndex);
	window.location.href = newUrl;
}

function selectAll(node) {
	var filelistNodes = document.getElementsByName("filePathList");
	for (var i = 0; i < filelistNodes.length; i++) {
		filelistNodes[i].checked = node.checked;
	}
}

/*修改字体*/
function changeFont(selectName){
	var oNewsText = document.getElementById("mySearchInfo");
	oNewsText.className=selectName;

	/*var trNodes = document.getElementsByName("filePathList");
	for(var i = 0; i < trNodes.length; i++){
		
		trNodes[i].className = selectName;
	}*/
	
}
