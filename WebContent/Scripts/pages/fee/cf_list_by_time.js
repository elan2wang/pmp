$(function(){
	var comId = getQueryString("comId");
	var proId = getQueryString("proId");
	var year = getQueryString("year");
	var month = getQueryString("month");
	var url;
	if (comId==null){
		url="loadCondoFeeList_ByProject?proId="+proId+"&year="+year;
	} else {
		url="loadCondoFeeList_ByCompany?comId="+comId+"&year="+year;
	}
	if (month!=null){
		url=url+"&month="+month;
	}
	$('#cf_list').flexigrid({
		url:url,
		dataType:"json",
        colModel:[
            { display: '小区', name: 'condoFeeItem', width: Width*0.15, sortable:true, align: 'center' },
            { display: '房号', name: 'house', width: Width*0.08, sortable:true, align: 'center' },
            { display: '业主', name: 'owner', width: Width*0.08, sortable:true, align: 'center' },
            { display: '月份', name: 'cfMonth', width: Width*0.04, sortable:true, align: 'center' },
            { display: '状态', name: 'state', width: Width*0.07, sortable:true, align: 'center' },
            { display: '应收金额', name: 'oughtMoney', width: Width*0.07, sortable:true, align: 'center' },
            { display: '实收金额', name: 'fetchMoney', width: Width*0.07, sortable:true, align: 'center' },
            { display: '录入时间', name: 'inputTime', width: Width*0.12, sortable:true, align: 'center' },
            { display: '备注', name: 'comment', width: Width*0.16, sortable:true, align: 'center' }
        ],
		searchitems:[
 		    { display: '房号', name: 'house', isdefault:false },
 		    { display: '业主', name: 'owner', isdefault:false },
 		    { display: '状态', name: 'state', isdefault:true },
 		    { display: '录入时间', name: 'inputTime', isDefault:false}
		],
		title:true,
		showSearch:true,
        height:Height*0.9,
        showcheckbox:true,
        nomsg: '没有符合条件的物业费记录',
        usepager:true,
        useRp:true,
        rp: 15,
		showTableToggleBtn: true
	});
});

