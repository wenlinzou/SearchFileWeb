var count = 0;
function addFile() {
	count++;
	var oTabNode = document.getElementById("fileid");
	
	var oTrNode = oTabNode.insertRow();

	var oTdNode_file = oTrNode.insertCell();
	var otdNode_del = oTrNode.insertCell();

	/*oTdNode_file.innerHTML = "<input type='file' name=filename"+count+"/>";
	otdNode_del.innerHTML = "<img src='1.jpg' alt='删除附件' onclick='deleteFile(this)'/>";*/
	
	oTdNode_file.innerHTML = "附件";
	oTdNode_file.className = "tdwordRight";
	otdNode_del.innerHTML = "<input type='file' name=filename"+count+"/><img src='1.jpg' alt='删除附件' onclick='deleteFile(this)'/>";
	// otdNode_del.innerHTML = '<a href="javascript:void(0)"
	// onclick="deleteFile(this)">删除附件</a>';
	alert(count);
}
function deleteFile(node) {
	var oTrNode = node.parentNode.parentNode;
	oTrNode.parentNode.removeChild(oTrNode);
}
