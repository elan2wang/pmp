/**
 * author: Elan Wang
 * email： shohokh@gmail.com
 * create: 2012-3-30
 * 
 * this script is used for user_edit.jsp
 */ 

/**
 * invoked when page onload
 * 
 * load rolelist and grouplist
 */
$(function(){
	$.ajax({
		type: "POST",
		url: "load_Role_list",
		dataType: "json",
		success: function(data){
			var roleSelector = $("#roleId");
			roleSelector.find('option').remove();
			//roleSelector加载option选项
			$.each( data.Rows,function(commentIndex, comment){
				roleSelector.append("<option value=\""+comment['roleId']+"\">"+comment['roleName']+"</option>");
			});
			var select_roleId = document.getElementById("roleId");
		  	
		  	var roleId = document.getElementById("roleID").value;
		  	var groupId = document.getElementById("groupID").value;
		  	
		  	UpdateSelectedItem(select_roleId,roleId);
		  	roleChange(groupId);
		  	
		}
	});
});

function UpdateSelectedItem(objSelect, objItemValue) {
    for (var i = 0; i < objSelect.options.length; i++) {
        if (objSelect.options[i].value == objItemValue) {
	        objSelect.options[i].selected=true;
            break;       
        }       
    }
}