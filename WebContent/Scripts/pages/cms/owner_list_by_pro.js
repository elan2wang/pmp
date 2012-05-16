/**
 * author: Elan Wang
 * email： shohokh@gmail.com
 * create:   2012-5-10
 * 
 * this script is used by the owner_list_by_pro.jsp
 */ 

$(function(){
	var proId = getQueryString("proId");
	var editURL = "getOwner?ownerId=";
	var editWindow = "#ownerEdit";
	var id = $(this).parent().parent().parent().sub;
	$('#owner_list').flexigrid({
		url:"loadOwnerList_ByPro?proId="+proId,
		dataType:"json",
        colModel:[
            { display: '姓名', name: 'ownerName', width: Width*0.1, sortable:true, align: 'center' },
            { display: '性别', name: 'gender', width: Width*0.1, sortable:true, align: 'center' },
            { display: '联系电话', name: 'mobile', width: Width*0.1, sortable:true, align: 'center' },
            { display: '房号', name: 'houseNum', width: Width*0.1, sortable:true, align: 'center' },
            { display: '房屋面积', name: 'houseArea', width: Width*0.1, sortable:true, align: 'center' },
            { display: '工作单位', name: 'organization', width: Width*0.25, sortable:true, align: 'center' }
        ],
        buttons:[
            { name: '添加业主', bclass: 'add', onpress: ownerAdd },
            { separator: true },
            { name: '业主信息导入', bclass:'delete', onpress: ownerImport }
		],
		searchitems:[
		    { display: '姓名', name: 'ownerName', isDefault:false },
		    { display: '联系电话', name: 'mobile', isDefault:false },
		    { display: '房号', name: 'houseNum', isDefault:true },
		],
		height:Height*0.98,
        showcheckbox:true,
        nomsg: '没有符合条件的业主记录',
        usepager:true,
        useRp:true,
        rp: 15,
		showTableToggleBtn: true,
		operation:true,
		operationcontent:'<a href="javascript:void(0)" onclick="openEditWindow(\''+editWindow+'\',\''+editURL+'\'+$(this).parent().parent().parent())">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"#\" onclick=\"selectBuildTab($(this).parent().parent().parent(),$(this).parent().parent().parent(),$(this).parent().parent().parent())\">删除</a>',
		operationWidth: Width*0.15
	});
});

function ownerAdd(){
	openAddWindow('#ownerAdd');
}

function ownerImport(){
	openAddWindow('#ownerImport');
}