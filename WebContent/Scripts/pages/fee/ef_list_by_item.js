/**
 * author: Elan Wang
 * email： shohokh@gmail.com
 * create:   2012-6-23
 * 
 * this script is used by the ef_list_by_item.jsp
 */ 

$(function(){
	var efiId = getQueryString("efiId");
	$('#efiId').val(efiId);
	$('#ef_list').flexigrid({
		url:"loadElectricFeeList_ByEFI?efiId="+efiId,
		dataType:"json",
        colModel:[
            { display: '小区', name: 'houseOwner.house.building.project.proName', width: Width*0.15, sortable:true, align: 'center' },
            { display: '房号', name: 'houseOwner.house.houseNum', width: Width*0.1, sortable:true, align: 'center' },
            { display: '业主', name: 'houseOwner.owner.ownerName', width: Width*0.1, sortable:true, align: 'center' },
            { display: '小区均摊金额', name: 'proMeterFee', width: Width*0.15, sortable:true, align: 'center' },
            { display: '电梯均摊金额', name: 'liftMeterFee', width: Width*0.15, sortable:true, align: 'center' },
            { display: '均摊金额合计', name: 'totalMoney', width: Width*0.15, sortable:true, align: 'center' }
        ],
        buttons:[
            { name: '电费修改', bclass: 'edit', onpress: ef_edit },
            { name: '删除电费记录', bclass: 'delete', onpress: ef_delete },
            { name: '删除电费项目', bclass: 'delete', onpress: efi_delete },
            { name: '导出电费项目', bclass: 'export', onpress: efExport}
		],
		searchitems:[
		    { display: '房号', name: 'houseOwner.house.houseNum'},
		    { display: '业主', name: 'houseOwner.owner.ownerName'}
		],
		showSearch:true,
		title:true,
		height:Height*0.83,
        showcheckbox:true,
        usepager:true,
        useRp:true,
        rp: 15,
		showTableToggleBtn: true
	});
});

function ef_edit(){
	var idStr = getSelectedIds();
	openEditWindow('#efEdit','selectElectricFee?action=edit&idStr='+idStr);
}

function ef_delete(){
	var idStr = getSelectedIds();
	if(!confirm("您将删除编号为："+idStr+"电费记录"))return;
	$.ajax({
		type: 'POST',
		url: 'ef_delete?idStr='+idStr,
		dataType:"json",
		success: function(data){
			alert(data.msg);
			window.location.href='ef_list_by_item.jsp?efiId='+getQueryString("efiId");
		}
	});
}

function efi_delete(){
	var efiId = $('#efiId').val();
	if(!confirm("你将删除编号为:"+efiId+"的项目，及其关联的电费清单"))return;
	$.ajax({
		type: 'POST',
		url: 'ef_item_delete?efiId='+efiId,
		dataType: "json",
		success: function(data){
			alert(data.msg);
			window.parent.location.href='ef_item_list.jsp';
		}
	});
}
function efExport(){
	var qtype="";
	var query="";
	var efiId = $('#efiId').val();
	$.blockUI({ message: "<h3><img src='../Images/loading4.gif'><br>正在生成文件，请等待...</h3>" });
	$.ajax({
		type: "POST",
		dataType: "json",
		url: "exportElectricFee?efiId="+efiId,
		data: [{name:'qtype',value:qtype},{name:'query',value:query}],
		success: function(data){
			$.unblockUI();
			var ifr = document.createElement("IFRAME");  
			document.body.appendChild(ifr);  
			ifr.src = data.download_link; 
		}
	});
}

function getSelectedIds(){
	var rowid,idString="";
	$("#ef_list td input:checked").each(function(){
		rowid=$(this).parent().parent().parent().attr("id");
		rowid=rowid.substr(3);
		idString+=rowid+",";
	});
	if(idString==""){
		alert("请选择要修改的电费记录");
		return;
	}
	idString=idString.substring(0,idString.length-1);
	return idString;
}


function selectAllOrNone(the){
	var checks=$(the).parent().parent().parent().find(":checkbox");
	if($(the).attr("checked")){
		checks.each(function(){
			$(this).attr("checked",true);
		});
	}
	else{
		checks.each(function(){
			$(this).attr("checked",false);
		});
	}	
}