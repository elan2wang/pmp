// JavaScript Document
$(function()
{
	//building.project.proId  赋值为选中的小区的Id
	objfc1=document.getElementById("building.project.proId");
	var projectId = parent.document.getElementById("frame.pageId").value;
	objfc1.value = projectId;
});
	function strim(str){
		return str.replace(/(^\s*)|(\s*$)/g,""); 
	}
function FormCheck(){
	objfc1=document.getElementById("building.builNum");
	objfc2=document.getElementById("building.unitTag");
	objfc3=document.getElementById("building.unitCount");
	objfc4=document.getElementById("building.floorCount");
	objfc5=document.getElementById("building.housePer");
	objfc6=document.getElementById("building.skipFloor");
	objfc7=document.getElementById("building.builType");
	
	objfc8=document.getElementById("building.project.proId");
	
	
	if(strim(objfc1.value)==""){
		alert("楼号不能为空");
		objfc1.focus();
		return (false);
	}
	if(strim(objfc2.value)==""){
		alert("请选择单元标识");
		objfc2.focus();
		return (false);
	}
	if(strim(objfc3.value)==""){
		alert("单元数不能为空");
		objfc3.focus();
		return (false);
	}
	if(strim(objfc4.value)==""){
		alert("楼层数不能为空");
		objfc4.focus();
		return (false);
	}
	if(strim(objfc5.value)==""){
		alert("单元层户数不能为空");
		objfc5.focus();
		return (false);
	}
	if(strim(objfc6.value)==""){
		alert("跳过楼层数不能为空");
		objfc6.focus();
		return (false);
	}
	if(strim(objfc7.value)==""){
		alert("请选择楼宇类型");
		objfc7.focus();
		return (false);
	}
	
	//检查楼号是否重复
	return check_Building(strim(objfc8.value),strim(objfc1.value),objfc1);
}
function check_Building(projectId,builNum,objfc1) {
	var url = "checkBuildingByBuilNumAndProjectId?projectId="+projectId+"&builNum="+builNum;
    	$.ajax({
			type: "POST",
			url: url,
			dataType:"json",
			success : function(data){					
				var result = data["result"];
				if(result=="Failed")
				{
					alert("已存在该楼号，请核对！");
					objfc1.select();
					 return false;
				}
				else
				{
					document.getElementById("form").submit();
					closeAddNewBuilding();
					return true;						
				}
			}
		});    	
}
