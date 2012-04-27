// JavaScript Document
$(function(){
	$(".content .innercontent").eq(0).show();

	$("#tab1").click(function(){
			$(".nav li").removeClass("active");	
			$(this).addClass('active');
			$(".content .innercontent").hide().eq(0).show();
			return false;
		});
	  $('#authlist').flexigrid({colModel: [
             { display: '序号',  width: 40,  align: 'center' },
             { display: '权限名称', width: 200, align: 'center' },
			 { display: '权限描述', width: 200, align: 'center' },
             { display: '是否可用', width: 200,align: 'center' },
			 { display: '是否管理权限', width: 200, align: 'center' },
             { display: '操作',  width: 220, sortable: true, align: 'center', align: 'center' }
             ],height:305});
	});

function openAddNewAuth(){
    $('#newAuth').window('open');
}
function closeAddNewAuth(){
    $('#newAuth').window('close');
}
function openEditAuth(id){
	$('#editAuth').window({href:'getAuthById?authId='+id});
    $('#editAuth').window('open');
}
function deleteRow(obj,id)
{
	alert("进入删除方法");
	alert(id);
	if(!confirm("您将删除该项目有关的楼宇及房屋所有的信息,确定删除吗?"))
	{
		return;
	}
	alert("进入删除方法2");
	$.ajax({
			  type: "POST",
			  url: "deleteAuthById?authId="+id,
			  dataType: "json",
			  success : function(data){
				  obj.hide();
			  }
		   });
}
function closeEditAuth(){
    $('#editAuth').window('close');
}
function openAuthRes(id){
	$('#auth_res').window({href:'getAuthRes?authID='+id});
    $('#auth_res').window('open');
}
function closeAuthRes(){
	$('#auth_res').window({});
    $('#auth_res').window('close');
}