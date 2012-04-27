/**
 * author: Elan Wang
 * email： shohokh@gmail.com
 * create:   2012-4-19
 * 
 * this script is used by the mod_list.jsp
 */ 
$(function(){
	$(".content .innercontent").eq(0).show();
	$('#module_list').flexigrid({
		colModel:
			[{ display:'序号', width:40, align:'center' },
			 { display:'模块名称', width:80, align:'center' },
			 { display:'模块级别', width:50, align:'center' },
			 { display:'模块链接', width:200, align:'center' },
			 { display:'是否启用', width:50, align:'center' },
			 { display:'管理模块', width:50, align:'center' },
			 { display:'操作', width:100, align:'center' }
			],
		height:310
	});
});

function openAddModule(){
    $('#newModule').window('open');
}
function closeAddModule(){
    $('#newModule').window('close');
}

var windowsOpened = false;
function openEditModule(id){
	$('#editModule').window({href:'getModule?modId='+id});
	if(windowsOpened){
		$('#editModule').window('refresh');
	}
	windowsOpened = true;
    $('#editModule').window('open');
}
function closeEditModule(){
	$('#editModule').window('close');
}

function deleteRow(obj,id){
	if(!confirm("确认删除该模块？"))
	{
		return;
	}
	$.ajax({
		  type: "POST",
		  url: "deleteModule?modId="+id,
		  dataType: "json",
		  success : function(data){
			  obj.hide();
		  }
	   });
}