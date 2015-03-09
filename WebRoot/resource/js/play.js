// 切换显示和隐藏
function changeDiv(showdiv) {
	var nodediv = document.getElementById(showdiv);
	if (nodediv.style.display == "none") {
		nodediv.style.display = "block";
	} else {
		nodediv.style.display = "none";
	}
}