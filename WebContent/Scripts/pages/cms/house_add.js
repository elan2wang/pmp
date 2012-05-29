// JavaScript Document
$(function()
{
	//building.project.proId  赋值为选中的小区的Id
	objfc1=document.getElementById("house.building.builId");
	var builId = parent.document.getElementById("frame.housepageId").value;
	alert("in house_add.js   builId="+builId);
	objfc1.value = projectId;
	alert(document.getElementById("house.building.builId").value);
});
function strim(str){
		return str.replace(/(^\s*)|(\s*$)/g,""); 
}
function FormCheck(){
	objfc1=document.getElementById("unit");
	objfc2=document.getElementById("houseNum");
	objfc3=document.getElementById("house.houseArea");			
	if(strim(objfc1.value)==""){
		alert("单元号不能为空");
		objfc1.focus();
		return (false);
	}
	if(strim(objfc2.value)==""){
		alert("房号不能为空");
		objfc2.focus();
		return (false);
	}
	if(strim(objfc3.value)==""){
		alert("房屋面积不能为空");
		objfc3.focus();
		return (false);
	}
	
	//单元号是否存在
	return check_House(strim(objfc8.value),strim(objfc1.value),objfc1);
	}
function check_House(projectId,builNum,objfc1) {
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
					closeAddNewHouse();
					return true;						
				}
			}
		});    	
}
function Close(){
	    closeAddNewHouse();
	}
