$(function(){
	
	$(".content .innercontent").eq(0).show();
	
	//var editURL = "editZone?zoneId=";
	//var editWindow = "#zoneEdit";
	
	$('#owner_list').flexigrid({
		url:"fire/loadFireInfoBakList",
		dataType:"json",
        colModel:[
            { display: '所属场地', name: 'zone.zoneName', width: Width*0.1, sortable:true, align: 'center' },
            { display: '设备编号', name: 'deviceNumber', width: Width*0.1, sortable:true, align: 'center' },
            { display: '接受数据时间', name: 'receiveTime', width: Width*0.2, sortable:true, align: 'center' },
            { display: '接受数据内容', name: 'receiveInfo', width: Width*0.1, sortable:true, align: 'center' },
            { display: '处理状态', name: 'state', width: Width*0.1, sortable:true, align: 'center' },
            { display: '处理时间', name: 'disposeTime', width: Width*0.2, sortable:true, align: 'center' },
            { display: '处理人', name: 'operator', width: Width*0.1, sortable:true, align: 'center' }
        ],
        buttons:[
                 { name: '删除历史记录', bclass:'delete', onpress: ownerDelete }
     		],
     	searchitems:[
     		    { display: '所属场地', name: 'zone.zoneName', isDefault:false },
     		    { display: '设备编号', name: 'deviceNumber', isDefault:false },
     		    { display: '接受数据时间', name: 'receiveTime', isDefault:true },
     		    { display: '接受数据内容', name: 'receiveInfo', isDefault:true },
     		    { display: '处理状态', name: 'state', isDefault:true },
     		    { display: '处理时间', name: 'disposeTime', isDefault:true },
     		    { display: '处理人', name: 'operator', isDefault:true }
     		],
		showSearch:true,
		height:Height*0.79,
	    showcheckbox:true,
	    nomsg: '没有符合条件的消控历史记录',
	    usepager:true,
	    useRp:true,
	    rp: 15,
		showTableToggleBtn: true
	});
});


function zoneAdd(){
	$('#zoneAdd').window('open');
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
		alert("请选择删除的消控历史记录!");
		return;
	}
	
	idString=idString.substring(0,idString.length-1);
	
	if(!confirm("您将删除编号为："+idString+"的历史记录!"))return;
    
	$.ajax({
		type: 'POST',
		url: 'deleteFireInfoBak?idStr='+idString,
		dataType:"json",
		success: function(data){
			alert(data.msg);
			window.location.href="fireInfoHistory_list.jsp";
		}
	});
	
}




