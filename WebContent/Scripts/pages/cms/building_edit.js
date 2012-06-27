// JavaScript Document
$(function(){
	//获取有下拉框的id
  	var builType = document.getElementById("building.builType");
    
  	//获取隐藏域中的值，即服务器传来的owner的属性值
  	var builTypeValue = document.getElementById("builType").value;
	UpdateSelectedItem(builType,builTypeValue);
    
});

function UpdateSelectedItem(objSelect, objItemValue) {   
    for (var i = 0; i < objSelect.options.length; i++) {
        if (objSelect.options[i].value == objItemValue) {  
       	 objSelect.options[i].selected = true; 
            break;       
        }       
    } 
}

function editFormCheck(){
	objfc1=document.getElementById("building.builNum");
	objfc2=document.getElementById("building.unitTag");
	objfc3=document.getElementById("building.unitCount");
	objfc4=document.getElementById("building.floorCount");
	objfc5=document.getElementById("building.housesPer");
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
	if(strim(objfc7.value)==""){
		alert("请选择楼宇类型");
		objfc7.focus();
		return (false);
	}
	document.getElementById("form").submit();
	closeEditBuild();
	return true;	
}
