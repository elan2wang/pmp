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
