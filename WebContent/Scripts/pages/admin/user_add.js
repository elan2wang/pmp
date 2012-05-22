/**
 * author: Elan Wang
 * email： shohokh@gmail.com
 * create: 2012-3-30
 * 
 * this script is used for user_add.jsp
 */ 

/**
 * invoked when page onload
 * 
 * load rolelist and grouplist
 */
function loadRoleList(){
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
			roleChange();
		}
	});
}

function roleChange(groupId){
	var roleId = document.getElementById('roleId').value;
	$.ajax({
		type: "POST",
		url: "load_Group_list?roleId="+roleId,
		dataType: "json",
		success: function(data){
			var groupSelector = $("#groupId");
			groupSelector.find('option').remove();
			//roleSelector加载option选项
			$.each( data.Rows,function(commentIndex, comment){
				if(groupId!='undefined' && groupId==comment['groupId']){
					groupSelector.append("<option selected='selected' value=\""+comment['groupId']+"\">"+comment['groupName']+"</option>");
				} else {
					groupSelector.append("<option value=\""+comment['groupId']+"\">"+comment['groupName']+"</option>");
				}
			});
		}
			
	});
}