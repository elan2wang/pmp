/**
 * JavaScript Document
 */


function owner_edit_init() {
 	//alert("in init method");
  	//获取有下拉框的id
  	var select_gender = document.getElementById("select_gender");
  	var select_nationality = document.getElementById("owner.nationality"); 
  	var select_ismarried = document.getElementById("owner.ismarried");
  	var select_identityType = document.getElementById("owner.identityType");
  	var select_projectId = document.getElementById("projectId");
  	
  	
  	//获取隐藏域中的值，即服务器传来的owner的属性值
  	var gender = document.getElementById("gender").value;
  	var nationality = document.getElementById("nationality").value; 
  	var ismarried = document.getElementById("ismarried").value;
  	var identityType = document.getElementById("identityType").value;
  	var proName = document.getElementById("proId").value;
  	var builId = document.getElementById("builId").value;
  	var houseId = document.getElementById("houseId2").value;
  	var objName = document.getElementById("objName").value;
  	if(objName=="org.pmp.vo.Project")
  	{
  		select_projectId.onfocus = function(){  
  			select_projectId.blur();  
  		};
  	}
    UpdateSelectedItem(select_gender,gender);
  	UpdateSelectedItem(select_nationality,nationality);
 	UpdateSelectedItem(select_ismarried,ismarried);
 	UpdateSelectedItem(select_identityType,identityType);
  	UpdateSelectedItem(select_projectId,proName);
  	UpdateSelectedItem(select_projectId,proName);	 
  	projectChanged(builId,houseId);
}


function UpdateSelectedItem(objSelect, objItemValue) {   
    for (var i = 0; i < objSelect.options.length; i++) {
        if (objSelect.options[i].value == objItemValue) {
	        objSelect.options[i].selected = true; 
            break;       
        }       
    } 
}    