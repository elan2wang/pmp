/**
 * JavaScript Document
 */

function epageInt() {
    objP1 = document.getElementById("eP1");
    objP2 = document.getElementById("eP2");
    objTab1 = document.getElementById("eTab1");
    objTab2 = document.getElementById("eTab2");
    objP1.style.display = "none";
    objP2.style.display = "none";
    objTab1.className = "ModuleTap";
    objTab2.className = "ModuleTap";
}
function eto(page) {
    objP1 = document.getElementById("eP1");
    objP2 = document.getElementById("eP2");
    objTab1 = document.getElementById("eTab1");
    objTab2 = document.getElementById("eTab2");
    if (page == "eP2") {
        epageInt();
        objP2.style.display = "block";
        objTab2.className = "ModuleTapOn";
    }
    if (page == "eP1") {
        epageInt();
        objP1.style.display = "block";
        objTab1.className = "ModuleTapOn";
    }
}
$(function(){
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
  	var houseNum = document.getElementById("houseNum2").value;
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
  	
  	projectChanged(builId,houseId,houseNum);
});

function UpdateSelectedItem(objSelect, objItemValue) {   
    for (var i = 0; i < objSelect.options.length; i++) {
        if (objSelect.options[i].value == objItemValue) {
	        objSelect.options[i].selected = true; 
            break;       
        }       
    } 
} 
function eFormCheck(){
	/* set hidden input projectName */
	document.getElementById("projectName").value = document.getElementById("projectId").options[document.getElementById("projectId").selectedIndex].text;
	/* set hidden input buildingNum */
	document.getElementById("buildingNum").value = document.getElementById("buildingId").options[document.getElementById("buildingId").selectedIndex].text;
	/* set hidden input houseNum */
	document.getElementById("houseNum").value = document.getElementById("houseId").options[document.getElementById("houseId").selectedIndex].text;
	
	eto('eP2');
}