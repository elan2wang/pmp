
$(function(){
	var comId = getQueryString("comId");
	var proId = getQueryString("proId");
	var year = getQueryString("year");
	var month = getQueryString("month");
	var url;
	if (comId==null){
		url="loadCondoFeeList_ByProject?proId="+proId+"&year="+year+"&month="+month;
	} else {
		url="loadCondoFeeList_ByCompany?comId="+comId+"&year="+year+"&month="+month;
	}
	$('#cf_list').flexigrid({
		url:url,
		dataType:"json",
        colModel:[
            { display: '房号', name: 'house', width: Width*0.1, sortable:true, align: 'center' },
            { display: '业主', name: 'owner', width: Width*0.15, sortable:true, align: 'center' },
            { display: '状态', name: 'state', width: Width*0.1, sortable:true, align: 'center' },
            { display: '应收金额', name: 'oughtMoney', width: Width*0.1, sortable:true, align: 'center' },
            { display: '实收金额', name: 'fetchMoney', width: Width*0.1, sortable:true, align: 'center' },
            { display: '录入时间', name: 'inputTime', width: Width*0.2, sortable:true, align: 'center' },
        ],
        buttons:[
            { name: '数据导出', bclass: 'add', onpress: dataImport },
			{ separator: true },
		],
		searchitems:[
 		    { display: '房号', name: 'house', isDefault:false },
 		    { display: '业主', name: 'owner', isDefault:false },
 		    { display: '状态', name: 'state', isDefault:true },
 		    { display: '录入时间', name: 'inputTime', isDefault:false }
		],
        height:Height*0.98,
        showcheckbox:true,
        nomsg: '没有符合条件的物业费记录',
        usepager:true,
        useRp:true,
        rp: 15,
		showTableToggleBtn: true
	});
});

function dataImport(){
	
}

function dataExport(){
	
}

