$(function(){
	Width = Width * 0.48;
    $('#publicItemList').flexigrid({
    	url:"loadPublicRepairItemList",
    	dataType:"json",
    	colModel: [
    	    { display: '项目名称',name:'itemName', width: Width*0.25, sortable:true, align: 'center' },
            { display: '所在小区',name:'project.proName', width: Width*0.2, sortable:true, align: 'center' },
            { display: '项目类型',name:'itemType', width: Width*0.17, sortable:true, align: 'center' }
    	],
        buttons : [
            {name: '添加维修项目', bclass: 'add', onpress : addPublicItem },
		    {separator: true},
            {name: '删除维修项目', bclass: 'delete', onpress : deletePublicItem },
	    ],
	    searchitems:[
	        { display: '所在小区', name: 'project.proName', isDefault:true },
	        { display: '项目名称', name: 'itemName', isDefault:false },
	        { display: '项目类型', name: 'itemType', isDefault:false }
	    ],
        height:Height*0.85,
        showcheckbox:true,
        showSearch:true,
        usepager: true,
	    useRp: true,
	    rp: 15,
	    operation:true,
	    operationcontent:'<a  href=\"javascript:void(0)\" onclick=\"openList($(this).parent().parent().parent());\">清单</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" onclick="openEditMaintain($(this).parent().parent().parent())">编辑</a>',
	    operationWidth: Width*0.17,
	});
});

function addPublicItem(){
	$('#newPublicItem').window('open');
}

function openEditMaintain(obj){
	var id=parseInt(obj.attr("id").substr(3));
	var url = 'getPublicRepairItemByID?fbiId='+id;
	openEditWindow("#editPublicItem",url);
}
function openList(obj){
	var id=parseInt(obj.attr("id").substr(3));
	document.getElementById("publicRepairList").src="fix_public_list.jsp?fbiId="+id;
}

function deletePublicItem(){
	var rowid,idString="";
	$("#publicList td input:checked").each(function(){
		rowid=$(this).parent().parent().parent().attr("id");
		rowid=rowid.substr(3);
		idString+=rowid+",";
	});
	if(idString==""){
		alert("请选择要删除的维修单");
		return;
	}
	idString=idString.substring(0,idString.length-1);
	if(!confirm("您将删除该维修单,确定删除吗？"))return;
	$.ajax({
	  type: "POST",
	  url: 'deletePublicRepairItem?idStr='+idString,
	  dataType: "json",
	  success : function(data){
		  alert("选中的维修单删除成功");
		  window.location.href = "fix_public_item_list.jsp";
	  }
	});
}			