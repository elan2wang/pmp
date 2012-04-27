/**
 * author: Elan Wang
 * email： shohokh@gmail.com
 * create:   2012-3-29
 * 
 * this script is used by the group_add.jsp
 */ 

function levelChanged(){
	var level = document.getElementById("level").value;
	if(level==3){
		//显示fatherGroupId和refDomain下拉框
		$('#span_fatherGroupId').attr('style','');
		$('#span_refDomain').attr('style','');
		$.ajax({
			type: "POST",
			url: "select_father_group?level="+level,
			dataType: "json",
			success: function(data){
				//refDomain下拉框设置
				var selector1 = $('#refDomain');
				selector1.attr('disabled',true);
				selector1.find('option').remove();
				//fatherGroupId下拉框设置
				var selector = $('#fatherGroupId');
				selector.attr('disabled',false);
				selector.find('option').remove();
				$.each( data.Rows,function(commentIndex, comment){
					selector.append("<option value=\""+comment['groupId']+"\">"+comment['groupName']+"</option>");
				});
			}
		});
	}
	else if(level==2){
		alert(level);
		//如果用户组级别为 2,则隐藏fatherGroupId下拉框,显示refDomain下拉框
		$('#span_fatherGroupId').attr('style','display:none');
		$('#span_refDomain').attr('style','');
		var selector1 = $('#fatherGroupId');
		selector1.find('option').remove();
		
		$.ajax({
			type: "POST",
			url: "select_ref_domain?level="+level,
			dataType: "json",
			success: function(data){
				//refDomain下拉框设置
				var selector = $('#refDomain');
				selector.attr('disabled',false);
				selector.find('option').remove();
				$.each( data.Rows,function(commentIndex, comment){
					selector.append("<option value=\""+comment['comName']+"\">"+comment['comName']+"</option>");
				});
			}
		});
	}
	else if(level==1){
		//如果用户组级别为 1,则隐藏fatherGroupId和refDomain下拉框
		$('#span_fatherGroupId').attr('style','display:none');
		$('#span_refDomain').attr('style','display:none');
		$('#fatherGroupId').find('option').remove();
		$('#refDomain').find('option').remove();
	}
	
}

function fateherGroupChanged(){

}
/**
 * author: ChrussyGe
 * email： chrussyge@gmail.com
 * create:   2012-3-29
 * 
 * this script is used by the group_add.jsp
 */ 
function addFormCheck(){
    document.getElementById("form1").submit();
	closeAddNewGroup();
}
function addClose(){
	closeAddNewGroup();
}
function editFormCheck(){
    document.getElementById("form2").submit();
	closeEditGroup();
}
function editClose(){
	closeEditGroup();
}