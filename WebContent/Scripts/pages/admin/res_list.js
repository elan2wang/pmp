// JavaScript Document
$(function(){
	$(".content .innercontent").eq(0).show();

	$("#tab1").click(function(){
			$(".nav li").removeClass("active");	
			$(this).addClass('active');
			$(".content .innercontent").hide().eq(0).show();
			return false;
		});
	  $('#reslist').flexigrid({colModel: [
             { display: '序号',  width: 40,  align: 'center' },
             { display: '资源名称', width: 100, align: 'center' },
			 { display: '资源类型', width: 100, align: 'center' },
			 { display: '资源链接', width: 100,align: 'center' },
			 { display: '资源描述', width: 200,align: 'center' },
             { display: '是否可用', width: 100,align: 'center' },
			 { display: '是否为系统权限', width: 200, align: 'center' },
             { display: '操作',  width: 220, sortable: true, align: 'center', align: 'center' }
             ],height:305});
	});
function openAddNewRes(){
    $('#newRes').window('open');
}
function closeAddNewRes(){
    $('#newRes').window('close');
}
function openEditRes(id){
	$('#editRes').window({href:'getResById?resId='+id});
    $('#editRes').window('open');
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
			  url: "deleteResById?resId="+id,
			  dataType: "json",
			  success : function(data){
				  obj.hide();
			  }
		   });
}
function closeEditRes(){
    $('#editRes').window('close');
}