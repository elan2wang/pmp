// JavaScript Document
$(function(){
	var proId;
	var coefficient;//表格高度系数
	if(parent.document.getElementById("frame.pageType").value=="all"){	
		proId = 0;
		coefficient=0.89;
	}
	else{
		proId=parseInt(parent.document.getElementById("frame.pageId").value);
		$('#top_info').css('display','block');
		coefficient=0.84;
	}
    $('#buildinglist').flexigrid({
    	url:"building_listBySessionHandler?"+"projectId="+proId,
    	dataType:"json",
		colModel: [
            { display: '楼号', name:'builNum',  width: Width*0.05, sortable:true, align: 'center' },
			{ display: '物业小区', name:'project',  width: Width*0.15, sortable:true, align: 'center' },
			{ display: '单元数', name:'unitCount',  width: Width*0.1, sortable:true, align: 'center' },
			{ display: '楼层数', name:'floorCount',  width: Width*0.1, sortable:true, align: 'center' },
			{ display: '单元层户数', name:'housesPer',  width: Width*0.1, sortable:true, align: 'center' },
			{ display: '跳过楼层数', name:'skipFloor',  width: Width*0.1, sortable:true, align: 'center' },
			{ display: '楼宇类型',  name:'builType', width: Width*0.1, sortable:true, align: 'center'},
			{ display: '备注', name:'builDesc',  width: Width*0.2, sortable:true, align: 'center' ,hide:'true'},
			{ display: '是否启用',  name:'enabled', width: Width*0.05, sortable:true, align: 'center' ,hide:'true'}
         ],
         searchitems:[
            { display: '楼号', name: 'builNum', isdefault:false },
            { display: '楼宇类型', name: 'unitCount', isdefault:true },
            { display: '楼层数', name: 'floorCount', isdefault:false },
            { display: '单元层户数', name: 'housesPer', isdefault:false }
         ],
         height:Height*coefficient,
         showcheckbox:true,
         showSearch:true,
         usepager: true,
 		 useRp: true,
 		 rp: 15,
 		 operation:true,
		 operationcontent:'<a href="javascript:void(0)" onclick="openEditBuild($(this).parent().parent().parent())">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"#\" onclick=\"parent.selectHouseTab($(this).parent().parent().parent())\">清单</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"#\" onclick=\"deleteBuilding($(this).parent().parent().parent(),$(this).parent().parent().parent());\">删除</a>',
		 operationWidth: Width*0.22
	});
});
function builImport(){
	openAddWindow('#builImport');
}

function openEditBuild(obj){
	var id=parseInt(obj.attr("id").substr(3));
	var url = 'get_building?buildingId='+id;
	openEditWindow("#editBuild",url);
}


function deleteBuilding(obj,objid){
	var id = parseInt(objid.attr("id").substr(3));
	if(!confirm("您将删除该楼宇相关的所有房屋的信息,确定删除吗?"))return;
	
	$.ajax({
		type: "POST",
        url: 'delete_building?buildingId='+id,
        dataType: "json",
        success : function(data){
            obj.hide();
	    }
    });
	
}

function setBuildingId(buidingId){
	document.getElementById("buildingId").value=buidingId;
}
