/**
 * author: Elan Wang
 * email： shohokh@gmail.com
 * create:   2012-5-14
 * 
 * this script is used by the cf_list_by_item.jsp
 */ 

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
            { display: '录入时间', name: 'inputTime', width: Width*0.12, sortable:true, align: 'center' },
            { display: '备注', name: 'comment', width: Width*0.25, sortable:true, align: 'center' }
        ],
        buttons:[
            { name: '缴费录入', bclass: 'add', onpress: cfInput }
		],
		searchitems:[
		    { display: '年份', name : 'cfYear' },
		    { display: '月份', name : 'cfMonth' },
		    { display: '状态', name : 'state', isdefault : true }
		],
		title:true,
		showSearch:true,
		height:Height*0.84,
        showcheckbox:true,
        nomsg: '没有符合条件的物业费记录',
        usepager:true,
        useRp:true,
        rp: 15,
		showTableToggleBtn: true
	});
});

function cfInput(){
	var rowid,idString="";
	$("#cf_list td input:checked").each(function(){
		rowid=$(this).parent().parent().parent().attr("id");
		rowid=rowid.substr(3);
		idString+=rowid+",";
	});
	if(idString==""){
		alert("请选择需要录入的物业费记录");
		return;
	}
	idString=idString.substring(0,idString.length-1);
	$.ajax({
		type: 'POST',
		url: 'pre_check?action=record&idStr='+idString,
		success: function(data){
			if(data.result=='success'){
				openEditWindow('#cfInput','selectCondoFee?action=record&idStr='+idString);
			}
			else{
				alert("您选中的物业费记录无法录入,可能有以下原因：\n(1)有记录尚未设置应收金额\n(2)有记录已经通过审核");
			}
		}
	});
}

function smsInform(){
	var houseId = getQueryString("houseId");
	$.ajax({
		type: 'POST',
		url: 'sms_inform?houseId='+houseId,
		success: function(data){
			if(data.result=='success'){
				
			}
			else{
				
			}
		}
	});
}
