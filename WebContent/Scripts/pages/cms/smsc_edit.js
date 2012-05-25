// JavaScript Document
$(function()
{
  	//获取有下拉框的id
  	var company = document.getElementById("comId");   	
  //获取隐藏域中的值 	
  	var comId = document.getElementById("hiddenComId").value;  		
    UpdateSelectedItem(company,comId);
});

function UpdateSelectedItem(objSelect, objItemValue) {
    for (var i = 0; i < objSelect.options.length; i++) {
        if (objSelect.options[i].value == objItemValue) {  
       	 objSelect.options[i].selected = true; 
            break;       
        }       
    } 
}   



	function FormCheck(){
//		objfc1=document.getElementById("project.proName");
//		objfc2=document.getElementById("project.proAddress");
//		objfc3=document.getElementById("project.deliveryTime");
//		if(objfc1.value==""){
//			alert("小区名称不能为空");
//			objfc1.focus();
//			return (false);
//		}
//		if(objfc2.value==""){
//			alert("小区地址不能为空");
//			objfc2.focus();
//			return (false);
//		}
//		if(objfc3.value==""){
//			alert("建成时间不能为空");
//			objfc3.focus();
//			return (false);
//		}
		document.getElementById("form2").submit();
		closeEditSMSC();
	}
	
	function editClose(){
		closeEditSMSC();
	}