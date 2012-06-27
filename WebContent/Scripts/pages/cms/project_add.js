
function FormCheck(){
	objfc1=document.getElementById("project.proName");
	objfc2=document.getElementById("project.proAddress");
	objfc3=document.getElementById("project.deliveryTime");
	objfc4=document.getElementById("project.proType");
	objfc5=document.getElementById("proDistrict");
	objfc6=document.getElementById("project.proStreet");
	
	if(strim(objfc1.value)==""){
		alert("小区名称不能为空");
		objfc1.focus();
		return (false);
	}
	if(strim(objfc2.value)==""){
		alert("小区地址不能为空");
		objfc2.focus();
		return (false);
	}
	if(strim(objfc4.value)==""){
		alert("请选择项目类型");
		objfc4.focus();
		return (false);
	}
	if(strim(objfc5.value)==""){
		alert("请选择所属地区");
		objfc5.focus();
		return (false);
	}
	if(strim(objfc6.value)==""){
		alert("请选择所属街道");
		objfc6.focus();
		return (false);
	}
	
	//检查项目名称是否重复
	return check_Project(strim(objfc1.value),objfc1);
	
}

function check_Project(proName,objfc1) {
	alert(proName);
	$.ajax({
		type: "POST",
		url: "isProjectExist?proName="+proName,
		dataType:"json",
		success : function(data){					
			var result = data["result"];
			if(result=="Failed"){
				alert("已存在同名小区，请核对！");
				objfc1.select();
				return false;
			}else {
				document.getElementById("form").submit();
				return true;						
			}
		}
	});    	
}
