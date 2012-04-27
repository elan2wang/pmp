// JavaScript Document
$(function(){
	$(".content .innercontent").eq(0).show();

	$("#tab1").click(function(){
			$(".nav li").removeClass("active");	
			$(this).addClass('active');
			$(".content .innercontent").hide().eq(0).show();
			return false;
		});
	  $('#rolelist').flexigrid({colModel: [
             { display: '序号',  width: 40,  align: 'center' },
             { display: '角色名称', width: 200, align: 'center' },
			 { display: '角色描述', width: 200, align: 'center' },
             { display: '是否可用', width: 200,align: 'center' },
			 { display: '是否管理角色', width: 200, align: 'center' },
             { display: '操作',  width: 220, sortable: true, align: 'center', align: 'center' }
             ],height:305});
	});
function openAddNewRole(){
    $('#newRole').window('open');
}
function closeAddNewRole(){
    $('#newRole').window('close');
}
function deleteRow(obj,id)
{
//	alert("进入删除方法");
//	alert(id);
	if(!confirm("您将删除该项目有关的楼宇及房屋所有的信息,确定删除吗?"))
	{
		return;
	}
    //alert("进入删除方法2");
	$.ajax({
		  type: "POST",
		  url: "deleteRoleById?roleId="+id,
		  dataType: "json",
		  success : function(data){
			  obj.hide();
		  }
	   });
}
function openEditRole(id){
	alert(id);
	$('#editRole').window({href:'getRoleById?roleId='+id});
    $('#editRole').window('open');
}
function closeEditRole(){
    $('#editRole').window('close');
}
function openRoleAuth(id){
	alert(id);
	$('#role_auth').window({href:'getRoleAuth?roleID='+id});
    $('#role_auth').window('open');
}
function closeRoleAuth(){
    $('#role_auth').window('close');
}
function openRoleMod(id){
	alert(id);
	$('#role_mod').window({href:'getRoleModule?roleId='+id});
    $('#role_mod').window('open');
}
function closeRoleMod(){
    $('#role_mod').window('close');
}
