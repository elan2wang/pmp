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
function loadInfo(){
	$.ajax({
		type: "POST",
		url: "load_role_group_list",
		dataType: "json",
		success: function(data){
			var roleSelector = $("#roleId");
			var groupSelector = $("#groupId");
			roleSelector.find('option').remove();
			groupSelector.find('option').remove();
			//roleSelector加载option选项
			$.each( data.Roles,function(commentIndex, comment){
				roleSelector.append("<option value=\""+comment['roleId']+"\">"+comment['roleName']+"</option>");
			});
			//groupSelector加载option选项
			$.each( data.Groups,function(commentIndex, comment){
				groupSelector.append("<option value=\""+comment['groupId']+"\">"+comment['groupName']+"</option>");
			});
			
		}
			
	});
}
function addFormCheck(){
    document.getElementById("form1").submit();
	window.parent.closeAddNewUser();
}
function addClose(){
    window.parent.closeAddNewUser();
}