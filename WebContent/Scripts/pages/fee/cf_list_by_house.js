$(function(){
	var houseId = getQueryString("houseId");
	$('#cf_list').flexigrid({
		url:"loadCondoFeeList_ByHouse?houseId="+houseId,
		dataType:"json",
        colModel:[
            { display: '年份', name: 'cfYear', width: Width*0.1, sortable:true, align: 'center' },
            { display: '月份', name: 'cfMonth', width: Width*0.1, sortable:true, align: 'center' },
            { display: '状态', name: 'state', width: Width*0.1, sortable:true, align: 'center' },
            { display: '应收金额', name: 'oughtMoney', width: Width*0.1, sortable:true, align: 'center' },
            { display: '实收金额', name: 'fetchMoney', width: Width*0.1, sortable:true, align: 'center' },
            { display: '录入时间', name: 'inputTime', width: Width*0.25, sortable:true, align: 'center' }
        ],
        buttons:[
            { name: '缴费录入', bclass: 'add', onpress: cfInput },
			{ separator: true },
			{ name: '短信催缴', bclass:'delete', onpress: smsInform }
		],
		searchitems:[
		    { display: '年份', name: 'cfYear', isDefault:false },
		    { display: '月份', name: 'cfMonth', isDefault:false },
		    { display: '状态', name: 'state', isDefault:true },
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

function cfInput(){
	openEditWindow('#cfInput','selectCondoFee?action=record&idStr=101,102,103');
}

function smsInform(){
	alert("短信催缴");
}
