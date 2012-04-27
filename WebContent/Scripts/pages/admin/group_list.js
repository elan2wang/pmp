// JavaScript Document
$(function(){
	$(".content .innercontent").eq(0).show();

	$("#tab1").click(function(){
			$(".nav li").removeClass("active");	
			$(this).addClass('active');
			$(".content .innercontent").hide().eq(0).show();
			return false;
		});
	  $('#grouplist').flexigrid({colModel: [
             { display: '序号',  width: 40,  align: 'center' },
             { display: '用户组名称', width: 200, align: 'center' },
			 { display: '用户组描述', width: 200, align: 'center' },
			 { display: '用户组级别', width: 100,align: 'center' },
			 { display: '用户组关联域', width: 200,align: 'center' },
             { display: '操作',  width: 220, sortable: true, align: 'center', align: 'center' }
             ],height:305});
	});
function openAddNewGroup(){
    $('#newGroup').window('open');
}
function closeAddNewGroup(){
    $('#newGroup').window('close');
}

var windowsOpened = false;
function openEditGroup(id){
	$('#editGroup').window({href:'getGroup?groupId='+id});
	if(windowsOpened){
		$('#editGroup').window('refresh');
	}
	windowsOpened = true;
    $('#editGroup').window('open');
}

function closeEditGroup(){
    $('#editGroup').window('close');
}

function deleteRow(obj,id){
	if(!confirm("您将删除与该用户组关联的所有用户，确认删除？"))
	{
		return;
	}
	$.ajax({
		  type: "POST",
		  url: "deleteGroup?groupId="+id,
		  dataType: "json",
		  success : function(data){
			  obj.hide();
		  }
	   });
}