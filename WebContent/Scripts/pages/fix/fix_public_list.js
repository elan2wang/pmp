var fbiId;
$(function(){
	fbiId = getQueryString("fbiId");
	var url = "loadPublicRepairList?fbiId="+fbiId;
    $('#publiList').flexigrid({
    	url: url,
    	dataType:"json",
    	colModel: [
    	    { display: '设备编号',name:'equipNum', width: Width*0.13, sortable:true, align: 'center' },
    	    { display: '维修日期', name:'repairDate',width: Width*0.2, sortable:true, align: 'center' },
    	    { display: '维修类型',name:'repairType', width: Width*0.13, sortable:true, align: 'center' },
    	    { display: '责任人',name:'dutyMan', width: Width*0.13, sortable:true, align: 'center' },
    	    { display: '状态',name:'state', width: Width*0.13, sortable:true, align: 'center' },
    	],
        buttons : [
            {name: '新建记录', bclass: 'add', onpress : addPublic },
		    {separator: true},
            {name: '删除记录', bclass: 'delete', onpress : deletePublics }
	    ],
	    searchitems:[
	        { display: '设备编号', name: 'equipNum', isDefault:true },
	        { display: '维修日期', name: 'repairDate', isDefault:false },
	        { display: '维修类型', name: 'repairType', isDefault:false },
	        { display: '状态', name: 'state', isDefault:false }
	    ],
        height:Height*0.83,
        showcheckbox:true,
        showSearch:true,
        title: true,
        usepager: true,
	    useRp: true,
	    rp: 15,
	    operation:true,
	    operationcontent:'<a href="javascript:void(0)" onclick="openEditPublic($(this).parent().parent().parent())">编辑</a>',
	    operationWidth: Width*0.1
	});
});

function addPublic(){
	$('#newPublic').window('open');
	$('#FBIID').val(fbiId);
}

function openEditPublic(obj){
	var id=parseInt(obj.attr("id").substr(3));
	var url = 'getPublicRepairByID?fbId='+id;
	openEditWindow("#editPublic",url);
}

function deletePublics(){
	var rowid,idString="";
	$("#publiList td input:checked").each(function(){
		rowid=$(this).parent().parent().parent().attr("id");
		rowid=rowid.substr(3);
		idString+=rowid+",";
	});
	if(idString==""){
		alert("请选择要删除的维修单");
		return;
	}
	idString=idString.substring(0,idString.length-1);
	alert(idString);
	if(!confirm("您将删除该维修单,确定删除吗？"))return;
	$.ajax({
	  type: "POST",
	  url: 'deletePublicRepair?idStr='+idString,
	  dataType: "json",
	  success : function(data){
		  alert("选中的维修单删除成功");
		  window.location.href = window.location.href;
	  }
	});
}			
		