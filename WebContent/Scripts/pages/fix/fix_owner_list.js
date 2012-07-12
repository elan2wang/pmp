// JavaScript Document

$(function(){
    $('#ownerRepairList').flexigrid({
    	url:"loadOwnerRepairList",
    	dataType:"json",
    	colModel: [
    	    { display: '单号',name:'opNum', width: Width*0.035, sortable:true, align: 'center' },
    	    { display: '小区', name:'houseOwner.house.building.project.proName',width: Width*0.1, sortable:true, align: 'center' },
    	    { display: '房号', name:'houseOwner.house.houseNum',width: Width*0.05, sortable:true, align: 'center' },
    	    { display: '报修人',name:'applyPerson', width: Width*0.05, sortable:true, align: 'center' },
    	    { display: '联系方式',name:'contactPhone', width: Width*0.13, sortable:true, align: 'center' },
    	    { display: '报修类型',name:'repairType', width: Width*0.08, sortable:true, align: 'center' },
    	    { display: '报修时间',name:'applyTime', width: Width*0.08, sortable:true, align: 'center' },
    	    { display: '状态',name:'state', width: Width*0.08, sortable:true, align: 'center'},
    	    { display: '人工费',name:'laborFee', width: Width*0.05, sortable:true, align: 'center' },
    	    { display: '材料费',name:'materialFee', width: Width*0.05, sortable:true, align: 'center' },
    	    { display: '费用合计',name:'totalFee', width: Width*0.05, sortable:true, align: 'center' }
    	],
        buttons : [
            {name: '添加维修单', bclass: 'add', onpress : addOwnerRepair},
            {separator: true},
            {name: '删除维修单', bclass: 'delete', onpress : deleteOwnerRepair}
	    ],
	    searchitems:[
	        { display: '小区', name: 'houseOwner.house.building.project.proName', isDefault:false },
	        { display: '报修类型', name: 'repairType', isDefault:true },
	        { display: '状态', name: 'state', isDefault:false }
	    ],
	    searchQueryStrs:[
            {selectName:'qtype1',queryStrName:'query1'},
            {selectName:'qtype2',queryStrName:'query2'}
     	],
        height:Height*0.9,
        showcheckbox:true,
        showSearch:true,
        usepager: true,
	    useRp: true,
	    rp: 15,
	    operation:true,
	    operationcontent:'<a href="javascript:void(0)" onclick="openEditOwnerRepair($(this).parent().parent().parent())">编辑</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href=\"javascript:void(0)\" onclick=\"openAttach($(this).parent().parent().parent());\">附件</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a  href=\"javascript:void(0)\" onclick=\"printOwnerRepair($(this).parent().parent().parent());\">打印</a>',
	    operationWidth: Width*0.13
	});
});

function addOwnerRepair(){
	$('#newOwnerRepair').window('open');
}

function deleteOwnerRepair(){
	var rowid,idString="";
	$("#ownerRepairList td input:checked").each(function(){
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
	  url: 'deleteOwnerRepair?idStr='+idString,
	  dataType: "json",
	  success : function(data){
		  alert(data.msg);
		  window.location.href = window.location.href;
	  }
	});
}			

function openEditOwnerRepair(obj){
	var id=parseInt(obj.attr("id").substr(3));
	var url = 'getOwnerRepairByID?opId='+id;
	openEditWindow("#editOwnerRepair",url);
}

function openAttach(obj){
	var id=parseInt(obj.attr("id").substr(3));
	var url = 'loadRepairAttachList?opId='+id;
	openEditWindow("#openAttach",url);
}

function printOwnerRepair(obj){
	url="printOwnerRepair?opId="+obj.attr("id").substr(3);
	window.open(url);
}
		
