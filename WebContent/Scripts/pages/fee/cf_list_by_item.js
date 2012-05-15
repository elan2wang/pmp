$(function(){
	var cfiId = getQueryString("cfiId");
	$('#cf_list').flexigrid({
		url:"loadCondoFeeList_ByCFI?cfiId="+cfiId,
		dataType:"json",
        colModel:[
            { display: '房号', name: 'house', width: Width*0.15, sortable:true, align: 'center' },
            { display: '业主', name: 'owner', width: Width*0.15, sortable:true, align: 'center' },
            { display: '年份', name: 'cfYear', width: Width*0.15, sortable:true, align: 'center' },
            { display: '月份', name: 'cfMonth', width: Width*0.1, sortable:true, align: 'center' },
            { display: '状态', name: 'state', width: Width*0.15, sortable:true, align: 'center' },
            { display: '应收金额', name: 'oughtMoney', width: Width*0.15, sortable:true, align: 'center' }
        ],
        buttons:[
            { name: '应收金额更改', bclass: 'add', onpress: cfEdit },
			{ separator: true },
			{ name: '数据导入', bclass:'delete', onpress: cfImport },
			{ separator: true },
			{ name: '数据导出', bclass:'delete', onpress: cfExport },
			{ separator: true },
			{ name: '删除项目', bclass:'delete', onpress: deleteItem }
		],
		searchitems:[
		    { display: '房号', name: 'house', isDefault:true }
		],
		height:Height,
        showcheckbox:true,
        nomsg: '没有符合条件的物业费记录',
        usepager:true,
        useRp:true,
        rp: 15,
		showTableToggleBtn: true
	});
});

function cfImport(){
	openAddWindow('#cfImport');
}

function cfExport(){
	
}

function cfEdit(){
	openEditWindow('#cfEdit','selectCondoFee?action=edit&idStr=101,102,103');
}

function deleteItem(){
	var cfiId = getQueryString("cfiId");
	alert("你将删除编号为"+cfiId+"的项目，及其关联的物业费清单");
	$.ajax({
		type: 'POST',
		url: 'cf_item_delete?cfiId='+cfiId,
		success: function(data){
			alert("项目删除成功");
			window.parent.location.href='cf_item_list.jsp';
		}
	});
}


