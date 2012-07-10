/**
 * author: Elan Wang
 * email： shohokh@gmail.com
 * create:   2012-6-23
 * 
 * this script is used by the ef_list_by_house.jsp
 */ 

$(function(){
	var houseId = getQueryString("houseId");
	$('#houseId').val(houseId);
	$('#ef_list').flexigrid({
		url:"loadElectricFeeList_ByHouse?houseId="+houseId,
		dataType:"json",
        colModel:[
            { display: '起始日期', name: 'electricFeeItem.beginDate', width: Width*0.15, sortable:true, align: 'center' },
            { display: '结束日期', name: 'electricFeeItem.endDate', width: Width*0.15, sortable:true, align: 'center' },
            { display: '小区均摊金额', name: 'proMeterFee', width: Width*0.15, sortable:true, align: 'center' },
            { display: '电梯均摊金额', name: 'liftMeterFee', width: Width*0.15, sortable:true, align: 'center' },
            { display: '均摊金额合计', name: 'totalMoney', width: Width*0.15, sortable:true, align: 'center' }
        ],
        buttons:[
            { name: '电费预存', bclass: 'add', onpress: efc_Add },
            { name: '缴费历史', bclass: 'add', onpress: efc_list }
		],
		title:true,
		height:Height*0.9,
        showcheckbox:true,
        usepager:true,
        useRp:true,
        rp: 15,
		showTableToggleBtn: true
	});
});

function efc_Add(){
	openAddWindow('#efcAdd');
}

function efc_list(){
	openEditWindow('#efcList','loadElectricFeeChargeList_ByHouse?houseId='+$('#houseId').val());
}
