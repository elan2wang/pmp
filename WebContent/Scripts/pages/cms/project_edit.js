// JavaScript Document
$(function()
{
  	//获取有下拉框的id
  	var proType = document.getElementById("project.proType");
  	var proDistrict = document.getElementById("proDistrict");
  //获取隐藏域中的值，即服务器传来的owner的属性值
  	
  	var isornotenabled = document.getElementById("project.enabled").value;
  	var isornotfireEnabled = document.getElementById("project.fireEnabled").value;
  	var type = document.getElementById("proType").value;
  	var district = document.getElementById("District").value;
  	var street = document.getElementById("proStreet").value;
  	
  	//设置启用与消防的启用
  	if(isornotenabled==document.getElementById("isenabled").value)
	{
		document.getElementById("isenabled").checked = true;
	}
	else if(isornotenabled==document.getElementById("notenabled").value)
	{
		document.getElementById("notenabled").checked = true;
	}
	
	if(isornotfireEnabled==document.getElementById("isfireEnabled").value)
	{
		document.getElementById("isfireEnabled").checked = true;
	}
	else if(isornotfireEnabled==document.getElementById("notfireEnabled").value)
	{
		document.getElementById("notfireEnabled").checked = true;
	}
  	
	
    UpdateSelectedItem(proType,type);
 	UpdateSelectedItem(proDistrict,district);
 	getStreets(street);		
});

function UpdateSelectedItem(objSelect, objItemValue) {   
    for (var i = 0; i < objSelect.options.length; i++) {
        if (objSelect.options[i].value == objItemValue) {  
       	 objSelect.options[i].selected = true; 
            break;       
        }       
    } 
}   


function strim(str){
	return str.replace(/(^\s*)|(\s*$)/g,""); 
}
	function FormCheck(){
		objfc1=document.getElementById("project.proName");
		objfc2=document.getElementById("project.proAddress");
		objfc3=document.getElementById("project.deliveryTime");
		objfc4=document.getElementById("project.proType");
		objfc5=document.getElementById("proDistrict");
		objfc6=document.getElementById("project.proStreet");
		alert(objfc4.value);
		alert(objfc5.value);
		alert(objfc6.value);
		if(strim(objfc1.value)==""){
			alert("小区名称不能为空");
			objfc1.focus();
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
		if(strim(objfc2.value)==""){
			alert("小区地址不能为空");
			objfc2.focus();
			return (false);
		}
		if(strim(objfc3.value)==""){
			alert("建成时间不能为空");
			objfc3.focus();
			return (false);
		}
		
		document.getElementById("form").submit();
		closeEditProject();
		return true;	
	}