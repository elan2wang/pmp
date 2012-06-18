// JavaScript Document
$(function(){
	var editURL = "getRes?resId=";
	var editWindow = "#editRes";
	
	$(".content .innercontent").eq(0).show();
	$("#tab1").click(function(){
		$(".nav li").removeClass("active");	
		$(this).addClass('active');
		$(".content .innercontent").hide().eq(0).show();
		return false;
	});
	$('#reslist').flexigrid({
		url: "loadResList",
		dataType: "json",
		colModel: [
             { display: '资源名称', name:"resName", width: Width*0.12, sortable:true, align: 'left' },
             { display: '资源类型', name:"resType", width: Width*0.05, sortable:true, align: 'center' },
             { display: '管理权限', name:"issys", width: Width*0.05, sortable:true, align: 'center' },
             { display: '是否可用', name:"enabled", width: Width*0.05, sortable:true, align: 'center' },
			 { display: '资源链接', name:"resLink", width: Width*0.2, sortable:true, align: 'left' },
			 { display: '资源描述', name:"resDesc", width: Width*0.33, sortable:true, align: 'left' },
        ],
        buttons:[
             { name: '添加资源', bclass: 'add', onpress: addRes },
             { separator: true },
             { name: '导入资源', bclass:'import', onpress: importRes },
          	 { separator: true },
             { name: '删除资源', bclass:'delete', onpress: deleteRes }
        ],
        searchitems:[
          	 { display: '资源名称', name : 'resName' },
          	 { display: '资源类型', name : 'resType' },
          	 { display: '资源链接', name : 'resLink' },
        ],
        showSearch:true,
		height:Height*0.82,
        showcheckbox:true,
        nomsg: '没有符合条件的资源',
        usepager:true,
        useRp:true,
        rp: 15,
		showTableToggleBtn: true,
		operation:true,
		operationcontent:'<a href="javascript:void(0)" onclick="openEditWindow(\''+editWindow+'\',\''+editURL+'\'+$(this).parent().parent().parent().attr(\'id\').substr(3))">编辑</a>',
		operationWidth: Width*0.1
	});
});

function addRes(){
	openAddWindow('#newRes');
}

function importRes(){
	openAddWindow('#importRes');
}

function deleteRes(){
	var rowid,idString="";
	$("#reslist td input:checked").each(function(){
		rowid=$(this).parent().parent().parent().attr("id");
		rowid=rowid.substr(3);
		idString+=rowid+",";
	});
	if(idString==""){
		alert("请选择资源");
		return;
	}
	idString=idString.substring(0,idString.length-1);
	if(!confirm("您将删除编号为："+idString+"的资源"))return;
	$.ajax({
		type: "POST",
		url: "deleteRes?idStr="+idString,
	    dataType: "json",
	    success : function(data){
	    	alert("删除成功");
	    	window.location.href = "res_list.jsp";
	    }
    });
}