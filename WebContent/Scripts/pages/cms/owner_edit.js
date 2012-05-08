/**
 * JavaScript Document
 */


function owner_edit_init() {
	alert("in init method");
	//获取有下拉框的id
	var select_gender = document.getElementById("select_gender");
	var select_nationality = document.getElementById("owner.nationality"); 
	var select_ismarried = document.getElementById("owner.ismarried");
	var select_identityType = document.getElementById("owner.identityType");
	var select_projectId = document.getElementById("projectId");
	var select_buildingId = document.getElementById("buildingId");
	var select_houseId = document.getElementById("houseId");
	alert(select_projectId);

	alert(select_buildingId);
	alert(select_houseId);
//	alert(select_gender);
	
	//获取隐藏域中的值，即服务器传来的owner的属性值
	var gender1= document.getElementById("gender");
//	alert(gender1);
	var gender = document.getElementById("gender").value;
	//alert(gender);
	var nationality = document.getElementById("nationality").value; 
	var ismarried = document.getElementById("ismarried").value;
	var identityType = document.getElementById("identityType").value;
	var proName = document.getElementById("proId").value;
	var builNum = document.getElementById("builId").value;
	var houseNum = document.getElementById("houseId2").value;
	
	alert(document.getElementById("proId"));

	alert(document.getElementById("builId"));
	alert(document.getElementById("houseId2"));
	
	alert(proName);
	alert(builNum);
	alert(houseNum);
	
	//UpdateSelectedItem(select_gender,gender);
	//UpdateSelectedItem(select_nationality,nationality);
	////UpdateSelectedItem(select_ismarried,ismarried);
	//UpdateSelectedItem(select_identityType,identityType);
	UpdateSelectedItem(select_projectId,proName);
	UpdateSelectedItem(select_buildingId,builNum);
	UpdateSelectedItem(select_houseId,houseNum);
}


function UpdateSelectedItem(objSelect, objItemValue) {   
	alert("in UpdateSelectedItem method "+ objItemValue);
	alert(objSelect.options.length);
     for (var i = 0; i < objSelect.options.length; i++) {
    	 alert(objSelect.options[i].value);
         if (objSelect.options[i].value == objItemValue) {  
        	 alert("找到相等的");
        	 objSelect.options[i].selected = true; 
        	 objSelect.onchange();
             break;       
         }       
     } 
     alert("跳出UpdateSelectedItem");
}       