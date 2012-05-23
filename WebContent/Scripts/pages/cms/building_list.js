// JavaScript Document
$(function(){
	var pageid;
	var coefficient;//表格高度系数
	if(parent.document.getElementById("frame.pageType").value=="all"){	
	    pageid = 0;
		$('#top_info').css("display","none");
		$('#top_info2').css("display","block");
		coefficient=0.86;
	   }
	   else{
		  pageid=parseInt(parent.document.getElementById("frame.pageId").value);
		  $('#top_info').css("display","block");
		  $('#top_info2').css("display","none");
		  coefficient=0.8;
	   }
		  $('#buildinglist').flexigrid({
				  url:"building_listBySessionHandler?"+"projectId="+pageid,
				  dataType:"json",
				  colModel: [
<<<<<<< HEAD
	             { display: '楼号', name:'builNum',  width: 100, align: 'center' },
				 { display: '单元数', name:'unitCount',  width: 100, align: 'center' },
	             { display: '楼层数', name:'floorCount',  width: 100,align: 'center' },
				 { display: '单元层户数', name:'housesPer',  width: 100, align: 'center' },
				 { display: '跳过楼层数', name:'skipFloor',  width: 100, align: 'center' },
				 { display: '楼宇类型',  name:'builType', width: 100, align: 'center'},
				 { display: '物业费标准', name:'condoFeeRate',  width: 100, align: 'center'},
				 { display: '备注', name:'builDesc',  width: 100, align: 'center' ,hide:'true'},
				 { display: '是否启用',  name:'enabled', width: 200, align: 'center' ,hide:'true'}
	             ],
	    		searchitems:[
	    		 		    { display: '楼号', name: 'builNum', isDefault:true },
	    		 		],
	             height:305,
=======
	                 { display: '楼号', name:'builNum',  width: 100, align: 'center' },
				     { display: '单元数', name:'unitCount',  width: 100, align: 'center' },
	                 { display: '楼层数', name:'floorCount',  width: 100,align: 'center' },
				     { display: '单元层户数', name:'housesPer',  width: 100, align: 'center' },
				     { display: '跳过楼层数', name:'skipFloor',  width: 100, align: 'center' },
				     { display: '楼宇类型',  name:'builType', width: 100, align: 'center'},
				     { display: '物业费标准', name:'condoFeeRate',  width: 100, align: 'center'},
				     { display: '备注', name:'builDesc',  width: 100, align: 'center' ,hide:'true'},
				     { display: '是否启用',  name:'enabled', width: 200, align: 'center' ,hide:'true'}
	             ],
	             height:Height*coefficient,
	             searchitems:[
	                { display: '楼号', name: 'builNum', isdefault:false },
	                { display: '单元数', name: 'unitCount', isdefault:false },
	                { display: '楼层数', name: 'floorCount', isdefault:false },
	                { display: '单元层户数', name: 'housesPer', isdefault:true }
	             ],
	             showSearch:true,
>>>>>>> 06f54611c7339a2a3aff189dd92ad074577aef01
	             showcheckbox:true,
	             usepager: true,
	     		 useRp: true,
	     		 rp: 15,
	     		 operation:true,
<<<<<<< HEAD
				operationcontent:'<a href="javascript:void(0)" onclick="openEditBuild($(this).parent().parent().parent())">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"#\" onclick=\"parent.selectHouseTab($(this).parent().parent().parent())\">清单</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"#\" onclick=\"deleteBuilding($(this).parent().parent().parent(),$(this).parent().parent().parent());\">删除</a>',
				operationWidth: Width*0.22
=======
				 operationcontent:'<a href="javascript:void(0)" onclick="openEditBuild($(this).parent().parent().parent())">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"javascript:void(0)\" onclick=\"parent.selectHouseTab($(this).parent().parent().parent())\">清单</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"javascript:void(0)\" onclick=\"deleteBuilding($(this).parent().parent().parent(),$(this).prev().prev().prev().prev().html());\">删除</a>',
				 operationWidth: Width*0.22
>>>>>>> 06f54611c7339a2a3aff189dd92ad074577aef01
			});
});
function builImport(){
	openAddWindow('#builImport');
}
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
