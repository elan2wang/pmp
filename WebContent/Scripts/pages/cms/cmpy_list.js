// JavaScript Document

$(function(){
	$(".content .innercontent").eq(0).show();

	$("#tab1").click(function(){
			$(".nav li").removeClass("active");	
			$(this).addClass('active');
			$(".content .innercontent").hide().eq(0).show();
			return false;
		});
	  $('#cmpylist').flexigrid({
		  	 url:"cmpy_listBySessionHandler",
		     dataType:"json",
		     colModel: [
             { display: '序号',  width: 40,  align: 'center' },
             { display: '公司名称', width: 200, align: 'center' },
			 { display: '注册日期', width: 200, align: 'center' },
             { display: '公司法人', width: 200,align: 'center' },
			 { display: '联系方式', width: 200, align: 'center' },
			 { display: '工商执照', width: 200, align: 'center',hide:'true' },
			 { display: '注册资金', width: 200, align: 'center' ,hide:'true'},
			 { display: '公司地址', width: 200, align: 'center' ,hide:'true'},
			 { display: '备注', width: 200, align: 'center' ,hide:'true'},
             { display: '操作',  width: 120, sortable: true, align: 'center', align: 'left' }
             ],height:305,
             showcheckbox:true,
             usepager: true,
     		 useRp: true,
     		 rp: 15,
     		 operation:true,
			operationcontent:'<a href="javascript:void(0)" onclick="openEditCmpy($(this).parent().parent().parent())">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"#\" onclick=\"deleteCmpy($(this).parent().parent().parent(),$(this).parent().parent().parent());\">删除</a>',
			operationWidth: Width*0.22});

	});
function openAddNewCmpy(){
			$('#newCmpy').window('open');
		}
function closeAddNewCmpy(){
			$('#newCmpy').window('close');
		}

function openEditCmpy(obj){
	var id=parseInt(obj.attr("id").substr(3));
	var url = 'getCompanyByID?comid='+id;
	openEditWindow("#editCmpy",url);
}
function closeEditCmpy(){
	 $('#editCmpy').window('close');
	}

function deleteCmpy(obj,objid){
	var id = parseInt(objid.attr("id").substr(3));
	alert("进入删除方法");
	alert(id);
	if(!confirm("您将删除该项目有关的楼宇及房屋所有的信息,确定删除吗?"))
	{
		return;
	}
	$.ajax({
		  type: "POST",
		  url: 'deleteCmpy?comid='+id,
		  dataType: "json",
		  success : function(data){
//			  if((data.success).equals("")){
			  obj.hide();
//			  }
		  }
	   });
}			
		
