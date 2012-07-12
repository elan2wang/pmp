$(function(){
	
	$(".content .innercontent").eq(0).show();
	
	var editURL = "editZone?zoneId=";
	
	var editWindow = "#zoneEdit";
	
	$('#owner_list').flexigrid({
		url:"fire/loadZoneList",
		dataType:"json",
        colModel:[
            { display: '场地名称', name: 'zoneName', width: Width*0.1, sortable:true, align: 'center' },
            { display: '所属小区', name: 'project.proName', width: Width*0.1, sortable:true, align: 'center' },
            { display: '类型', name: 'zoneType', width: Width*0.1, sortable:true, align: 'center' },
            { display: '图片地址', name: 'zoneImgUrl', width: Width*0.1, sortable:true, align: 'center' },
            { display: '配置文件地址', name: 'zoneConfigUrl', width: Width*0.1, sortable:true, align: 'center' },
            { display: '描述', name: 'zoneDesc', width: Width*0.25, sortable:true, align: 'center' }
        ],
        buttons:[
                 { name: '添加场地', bclass: 'add', onpress: zoneAdd },
                 { separator: true },
                 { name: '删除场地', bclass:'delete', onpress: ownerDelete }
     		],
     	searchitems:[
     		    { display: '所属小区', name: 'project.proName', isDefault:false },
     		    { display: '场地名称', name: 'zoneName', isDefault:false },
     		    { display: '类型', name: 'zoneType', isDefault:true }
     		],
		showSearch:true,
		height:Height*0.78,
	    showcheckbox:true,
	    nomsg: '没有符合条件的场地记录',
	    usepager:true,
	    useRp:true,
	    rp: 15,
		showTableToggleBtn: true,
		operation:true,
		operationcontent:'<a href="javascript:void(0)" onclick="openEditWindow(\''+editWindow+'\',\''+editURL+'\'+$(this).parent().parent().parent().attr(\'id\').substr(3))">编辑</a>',
		operationWidth: Width*0.15
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
		alert("请选择删除的场地记录!");
		return;
	}
	
	idString=idString.substring(0,idString.length-1);
	
	if(!confirm("您将删除编号为："+idString+"的场地!"))return;
    	
	
	$.ajax({
		type: 'POST',
		url: 'deleteZone?idStr='+idString,
		dataType:"json",
		success: function(data){
			alert(data.msg);
			window.location.href="zone_list.jsp";
		}
	});
	
}




