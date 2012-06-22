// JavaScript Document

$(function(){
    $('#managelist').flexigrid({
    	url:"loadOwnerRepairList",
    	dataType:"json",
    	colModel: [
    	    { display: '单号',name:'opNum', width: Width*0.035, align: 'center' },
    	    { display: '小区', name:'houseOwner.house.building.project.proName',width: Width*0.08, align: 'center' },
    	    { display: '房号', name:'houseOwner.house.houseNum',width: Width*0.05, align: 'center' },
    	    { display: '报修人',name:'applyPerson', width: Width*0.05,align: 'center' },
    	    { display: '联系方式',name:'contactPhone', width: Width*0.15, align: 'center' },
    	    { display: '报修类型',name:'repairType', width: Width*0.08, align: 'center' },
    	    { display: '报修时间)',name:'applyTime', width: Width*0.08, align: 'center' },
    	    { display: '状态',name:'state', width: Width*0.08, align: 'center'},
    	    { display: '人工费',name:'laborFee', width: Width*0.05, align: 'center' },
    	    { display: '材料费',name:'materialFee', width: Width*0.05, align: 'center' },
    	    { display: '费用合计',name:'totalFee', width: Width*0.05, align: 'center' }
    	],
        buttons : [
            {name: '添加维修单', bclass: 'add', onpress : addMaintain},
            {name: '删除维修单', bclass: 'delete', onpress : deleteMaintain},
            {name: '导出维修单', bclass: 'excel', onpress : deleteMaintain},
		    {separator: true}
	    ],
	    searchitems:[
	        { display: '报修人', name: 'mtManager', isDefault:true },
	        { display: '地址', name: 'mtAdress', isDefault:true },
	        { display: '报修内容', name: 'mtContent', isDefault:true }
	    ],
        height:Height*0.82,
        showcheckbox:true,
        showSearch:true,
        usepager: true,
	    useRp: true,
	    rp: 15,
	    operation:true,
	    operationcontent:'<a href="javascript:void(0)" onclick="openEditMaintain($(this).parent().parent().parent())">编辑</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href=\"javascript:void(0)\" onclick=\"openAttach($(this).parent().parent().parent());\">附件</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a  href=\"javascript:void(0)\" onclick=\"printAttach($(this).parent().parent().parent());\">打印</a>',
	    operationWidth: Width*0.1
	});
});

function addMaintain(){
	$('#newOwnerRepair').window('open');
}

function openEditMaintain(obj){
	var id=parseInt(obj.attr("id").substr(3));
	var url = 'getOwnerRepairByID?opId='+id;
	openEditWindow("#editOwnerRepair",url);
}
function openAttach(obj){
	var id=parseInt(obj.attr("id").substr(3));
	var url = 'getMaintainByID?comid='+id;
	openEditWindow("#openAttach",url);
}
function printAttach(obj){
	alert("print Attachment");
	url="mt_manage_print.jsp?id="+obj.attr("id").substr(3);
	window.open(url);//打开另外一个链接
}
function deleteMaintain(){
	var rowid,idString="";
	$("#houselist td input:checked").each(function(){
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
	  url: 'deleteMaintain?idStr='+idString,
	  dataType: "json",
	  success : function(data){
		  alert("选中的维修单删除成功");
		  window.location.href = window.location.href;
	  }
	});
}			
		
