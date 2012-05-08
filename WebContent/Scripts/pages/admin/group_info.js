/**
 * author: Elan Wang
 * email： shohokh@gmail.com
 * create:   2012-3-29
 * 
 * this script is used by the group_add.jsp
 */ 

function levelChanged(){
	var level = document.getElementById("level").value;
	var selector1 = $('#refDomain');
	selector1.find('option').remove();
	var selector = $('#fatherGroupId');
	selector.find('option').remove();
	
	if (level == 1){
		selector.attr('disabled',true);
		selector1.attr('disabled',true);
		selector1.append("<option value='空关联域'>空关联域</option>");
		selector.append("<option value=0>0:根用户组</option>");
	} else {
		$.ajax({
			type: "POST",
			url: "levelChange?level="+level,
			dataType: "json",
			success: function(data){
				if (level == 2){
					selector.append("<option value=0>0:根用户组</option>");
					selector.attr('disabled',true);
					selector1.attr('disabled',false);
					$.each( data.Rows,function(commentIndex, comment){
						selector1.append("<option value=\""+comment['comName']+"\">"+comment['comName']+"</option>");
					});
				}
				if (level == 3){
					selector.attr('disabled',false);
					selector1.attr('disabled',false);
					$.each( data.group,function(commentIndex, comment){
						selector.append("<option value=\""+comment['groupId']+"\">"+comment['groupName']+"</option>");
					});
					$.each( data.project,function(commentIndex, comment){
						selector1.append("<option value=\""+comment['proName']+"\">"+comment['proName']+"</option>");
					});
				}
				
			}
		});
	
	}
	
	
}

function fatherGroupChanged(){
	var fatherGroup = document.getElementById("fatherGroupId").value;
	var selector1 = $('#refDomain');
	selector1.find('option').remove();
	$.ajax({
		type: "POST",
		url: "fatherChange?fatherGroup="+fatherGroup,
		dataType: "json",
		success: function(data){
            $.each( data.Rows,function(commentIndex, comment){
				selector1.append("<option value=\""+comment['proName']+"\">"+comment['proName']+"</option>");
			});
		}
	});
}