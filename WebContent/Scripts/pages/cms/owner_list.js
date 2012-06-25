/**
 * author: Elan Wang
 * email： shohokh@gmail.com
 * create:   2012-5-10
 * 
 * this script is used by the owner_list.jsp
 */ 

$(function(){
	$(".content .innercontent").eq(0).show();
	var editURL = "getOwner?ownerId=";
	var editWindow = "#ownerEdit";
	
	$('#owner_list').flexigrid({
		url:"loadOwnerList_ByPro",
		dataType:"json",
        colModel:[
			{ display: '小区', name: 'house.building.project.proName', width: Width*0.1, sortable:true, align: 'center' },
			{ display: '房号', name: 'house.houseNum', width: Width*0.08, sortable:true, align: 'center' },
			{ display: '姓名', name: 'owner.ownerName', width: Width*0.08, sortable:true, align: 'center' },
            { display: '性别', name: 'owner.gender', width: Width*0.05, sortable:true, align: 'center' },
            { display: '手机号码', name: 'owner.mobile', width: Width*0.1, sortable:true, align: 'center' },
            { display: '家庭电话', name: 'owner.homePhone', width: Width*0.1, sortable:true, align: 'center' },
            { display: '房屋面积', name: 'house.houseArea', width: Width*0.08, sortable:true, align: 'center' },
            { display: '工作单位', name: 'owner.organization', width: Width*0.22, sortable:true, align: 'center' }
        ],
        buttons:[
            { name: '添加业主', bclass: 'add', onpress: ownerAdd },
            { separator: true },
            { name: '业主信息导入', bclass:'import', onpress: ownerImport },
            { separator: true },
            { name: '删除业主', bclass:'delete', onpress: ownerDelete }
		],
		searchitems:[
			{ display: '小区', name: 'house.building.project.proName', isDefault:false },
			{ display: '楼号', name: 'house.building.builNum', isDefault:false },
			{ display: '房号', name: 'house.houseNum', isDefault:false },
		    { display: '姓名', name: 'owner.ownerName', isDefault:true },
		    { display: '手机号码', name: 'owner.mobile', isDefault:false }
		],
		searchQueryStrs:[
             {selectName:'qtype1',queryStrName:'query1'},
             {selectName:'qtype2',queryStrName:'query2'}
      	],
		showSearch:true,
		height:Height*0.88,
        showcheckbox:true,
        nomsg: '没有符合条件的业主记录',
        usepager:true,
        useRp:true,
        rp: 15,
		showTableToggleBtn: true,
		operation:true,
		operationcontent:'<a href="javascript:void(0)" onclick="openEditWindow(\''+editWindow+'\',\''+editURL+'\'+$(this).parent().parent().parent().attr(\'id\').substr(3))">编辑</a>',
		operationWidth: Width*0.08
	});
});

function ownerAdd(){
	$('#ownerAdd').window('open');
}

function ownerImport(){
	openAddWindow('#ownerImport');
}

function ownerDelete(){
	var rowid,idString="";
	$("#owner_list td input:checked").each(function(){
		rowid=$(this).parent().parent().parent().attr("id");
		rowid=rowid.substr(3);
		idString+=rowid+",";
	});
	if(idString==""){
		alert("请选择删除的业主记录");
		return;
	}
	idString=idString.substring(0,idString.length-1);
	if(!confirm("您将删除编号为："+idString+"的业主"))return;
	$.ajax({
		type: 'POST',
		url: 'deleteOwner?idStr='+idString,
		success: function(data){
			alert("业主记录删除成功");
			window.location.href=window.location.href;
		}
	});
}
