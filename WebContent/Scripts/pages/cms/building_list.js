// JavaScript Document
$(function(){
	var pageid;
	if(parent.document.getElementById("frame.pageType").value=="all"){	
	    pageid = 0;
		$('#top_info').css("display","none");
		$('#top_info2').css("display","block");
		  $('#building_data').html(AddTds(20,13));
	   }
	   else{
		  pageid=parseInt(parent.document.getElementById("frame.pageId").value);
		  $('#top_info').css("display","block");
		  $('#top_info2').css("display","none");
		  $('#building_data').html(AddTds(20,11));
	   }
		  $('#buildinglist').flexigrid({
				  url:"building_listBySessionHandler?"+"projectId="+pageid,
				  dataType:"json",
				  colModel: [
	             { display: '楼号', name:'builNum',  width: 100, align: 'center' },
				 { display: '单元数', name:'unitCount',  width: 100, align: 'center' },
	             { display: '楼层数', name:'floorCount',  width: 100,align: 'center' },
				 { display: '单元层户数', name:'housesPer',  width: 100, align: 'center' },
				 { display: '跳过楼层数', name:'skipFloor',  width: 100, align: 'center' },
				 { display: '楼宇类型',  name:'builType', width: 100, align: 'center'},
				 { display: '物业费标准', name:'condoFeeRate',  width: 100, align: 'center'},
				 { display: '备注', name:'builDesc',  width: 100, align: 'center' ,hide:'true'},
				 { display: '是否启用',  name:'enabled', width: 200, align: 'center' ,hide:'true'}
	             ],height:305,
	             showcheckbox:true,
	             usepager: true,
	     		 useRp: true,
	     		 rp: 15,
	     		 operation:true,
				operationcontent:'<a href="javascript:void(0)" onclick="openEditBuild($(this).parent().parent().parent())">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"#\" onclick=\"parent.selectHouseTab($(this).parent().parent().parent())\">清单</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"#\" onclick=\"deleteBuilding($(this).parent().parent().parent(),$(this).prev().prev().prev().prev().html());\">删除</a>',
				operationWidth: Width*0.22
			});
});
function openAddNewBuild(){
	         $win=$('#newBuild').window({
                top:10,   
                left:($(window).width() - 450) * 0.5
			 });
	         
	         $win.window('open');
		}
function closeAddNewBuild(){
			$('#newBuild').window('close');
		}
function openEditBuild(obj){
	var id=parseInt(obj.attr("id").substr(3));
	var url = 'get_building?buildingId='+id;
	openEditWindow("#editBuild",url);
		}


function deleteBuilding(obj,objid){
	var id = parseInt(objid.attr("id").substr(3));
	alert("进入删除方法");
	alert("building"+id);
	if(!confirm("您将删除该楼宇相关的所有房屋的信息,确定删除吗?"))
	{
		return;
	}
	$.ajax({
		  type: "POST",
		  url: 'delete_building?buildingId='+id,
		  dataType: "json",
		  success : function(data){
//			  if((data.success).equals("")){
			  obj.hide();
//			  }
		  }
	   });
	
}
function closeEditBuild(){
	        $('#editBuild').window('close');
	    }
function setBuildingId(buidingId){
	document.getElementById("buildingId").value=buidingId;
}
