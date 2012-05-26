// JavaScript Document
$(function(){
	//building.project.proId  赋值为选中的小区的Id
	objfc1=document.getElementById("house.building.builId");
	var builId = parent.document.getElementById("frame.housepageId").value;
	objfc1.value = builId;
		var isornotempty = document.getElementById("house.isempty");
		if(isornotempty.value==document.getElementById("isempty").value)
		{
			document.getElementById("isempty").checked = true;
		}
		else if(isornotempty.value==document.getElementById("notempty").value)
		{
			document.getElementById("notempty").checked = true;
		}
});
function strim(str){
	return str.replace(/(^\s*)|(\s*$)/g,""); 
}
function FormCheck(){
	objfc1=document.getElementById("house.houseArea");
	if(strim(objfc1.value)==""){
		alert("房屋面积不能为空");
		objfc1.focus();
		return (false);
	}
	document.getElementById("form").submit();
	closeEditHouse();
	}
function Close(){
	    closeEditHouse();
	}
