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
		if(validate_disk(diskname,diskwarn,"errordisk")==false){
			diskname.focus();
			return false;
		}
		
	}
}