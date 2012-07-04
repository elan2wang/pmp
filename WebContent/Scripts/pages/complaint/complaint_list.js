// JavaScript Document

$(function(){
    $('#complaintList').flexigrid({
    	url:"loadComplaintList",
    	dataType:"json",
    	colModel: [
    	    { display: '投诉人',name:'compPerson', width: Width*0.035, sortable:true, align: 'center' },
    	    { display: '投诉人电话', name:'compTel',width: Width*0.1, sortable:true, align: 'center' },
    	    { display: '投诉时间', name:'compTime',width: Width*0.05, sortable:true, align: 'center' },
    	    { display: '投诉原因',name:'compContent', width: Width*0.05, sortable:true, align: 'center' },
    	    { display: '处理结果',name:'handleResult', width: Width*0.13, sortable:true, align: 'center' },
    	    { display: '经办人',name:'handlePerson', width: Width*0.08, sortable:true, align: 'center' },
    	    { display: '处理时间',name:'handleTime', width: Width*0.08, sortable:true, align: 'center' },
    	],
        buttons : [
            {name: '添加投诉记录', bclass: 'add', onpress : addComplaint},
            {separator: true},
            {name: '删除投诉记录', bclass: 'delete', onpress : deleteComplaint}
	    ],
	    searchitems:[
	        { display: '处理结果', name: 'state', isDefault:false }
	    ],
	    searchQueryStrs:[
            {selectName:'qtype1',queryStrName:'query1'}
     	],
        height:Height*0.96,
        showcheckbox:true,
        showSearch:true,
        usepager: true,
	    useRp: true,
	    rp: 15,
	    operation:true,
	    operationcontent:'<a href="javascript:void(0)" onclick="openEditComplaint($(this).parent().parent().parent())">编辑</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a  href=\"javascript:void(0)\" onclick=\"browseComplaint($(this).parent().parent().parent());\">查看</a>',
	    operationWidth: Width*0.13
	});
});

function addComplaint(){
	$('#newComplaint').window('open');
}

function deleteComplaint(){
	var rowid,idString="";
	$("#complaintList td input:checked").each(function(){
		rowid=$(this).parent().parent().parent().attr("id");
		rowid=rowid.substr(3);
		idString+=rowid+",";
	});
	if(idString==""){
		alert("请选择要删除的投诉记录");
		return;
	}
	idString=idString.substring(0,idString.length-1);
	alert(idString);
	if(!confirm("您将删除该记录,确定删除吗？"))return;
	$.ajax({
	  type: "POST",
	  url: 'deleteComplaint?idStr='+idString,
	  dataType: "json",
	  success : function(data){
		  alert("选中的记录删除成功");
		  window.location.href = window.location.href;
	  }
	});
}			

function openEditComplaint(obj){
	var id=parseInt(obj.attr("id").substr(3));
	var url = 'getComplaintById?compId='+id;
	openEditWindow("#editComplaint",url);
}


function browseComplaint(obj){
	var id=parseInt(obj.attr("id").substr(3));
	var url = 'browseComplaintById?compId='+id;
	openEditWindow("#browseComplaint",url);
}
		
