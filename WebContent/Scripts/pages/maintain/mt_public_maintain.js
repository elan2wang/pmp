$(function(){
    $('#maintainlist').flexigrid({
    	url:"",
    	dataType:"json",
    	colModel: [
    	    { display: '维修项目名称',name:'mtName', width: Width*0.6, align: 'center' },
    	    { display: '维修项目类型',name:'mtStyle', width: Width*0.3, align: 'center',hide:'true' }
    	],
        buttons : [
            {name: '添加维修项目', bclass: 'add', onpress : addMaintain},
            {name: '删除维修项目', bclass: 'add', onpress : deleteMaintain},
		    {separator: true}
	    ],
	    searchitems:[
	        { display: '项目类型', name: 'mtStyle', isDefault:true }
	    ],
        height:Height*0.82,
        showcheckbox:true,
        showSearch:true,
        usepager: true,
	    useRp: true,
	    rp: 15,
	    operation:true,
	    operationcontent:'<a  href=\"javascript:void(0)\" onclick=\"openList($(this).parent().parent().parent());\">清单</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" onclick="openEditMaintain($(this).parent().parent().parent())">编辑</a>',
	    operationWidth: Width*0.35
	});
});

function addMaintain(){
	$('#newMaintain').window('open');
}

function openEditMaintain(obj){
	var id=parseInt(obj.attr("id").substr(3));
	var url = 'getMaintainByID?comid='+id;
	openEditWindow("#editMaintain",url);
}
function openList(obj){
	var id=parseInt(obj.attr("id").substr(3));
	//var url = 'getMaintainByID?comid='+id;
	parent.document.getElementById("maintainProList").src="mt_public_prolist.jsp";
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