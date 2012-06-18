$(function(){
	var cfiId = getQueryString("cfiId");
	/* set the value of the hidden input */
	document.getElementById("cfiId").value = cfiId;
	
	$('#cf_list').flexigrid({
		url:"loadCondoFeeList_ByCFI?cfiId="+cfiId,
		dataType:"json",
        colModel:[
            { display: '房号', name: 'house', width: Width*0.06, sortable:true, align: 'center' },
            { display: '业主', name: 'owner', width: Width*0.1, sortable:true, align: 'center' },
            { display: '年份', name: 'cfYear', width: Width*0.05, sortable:true, align: 'center' },
            { display: '月份', name: 'cfMonth', width: Width*0.05, sortable:true, align: 'center' },
            { display: '状态', name: 'state', width: Width*0.05, sortable:true, align: 'center' },
            { display: '应收金额', name: 'oughtMoney', width: Width*0.06, sortable:true, align: 'center' },
            { display: '实收金额', name: 'fetchMoney', width: Width*0.06, sortable:true, align: 'center' },
            { display: '录入时间', name: 'inputTime', width: Width*0.1, sortable:true, align: 'center' },
            { display: '录入人员', name: 'recordPerson', width: Width*0.1, sortable:true, align: 'center' },
            { display: '备注', name: 'comment', width: Width*0.2, sortable:true, align: 'center' }
        ],
        buttons:[
			{ name: '数据导入', bclass:'import', onpress: cfImport },
			{ separator: true },
			{ name: '数据修改', bclass: 'edit', onpress: cfEdit },
			{ separator: true },
			{ name: '删除条目', bclass:'delete', onpress: deleteList },
			{ separator: true },
			{ name: '删除项目', bclass:'delete', onpress: deleteItem },
			{ separator: true },
			{ name: '缴费审核', bclass:'check', onpress: cfAudit }
		],
		searchitems:[
		    { display: '年份', name: 'cfYear', isdefault:false },
		    { display: '月份', name: 'cfMonth', isdefault:false },
		    { display: '状态', name: 'state', isdefault:false },
		    { display: '录入时间', name: 'inputTime', isdefault:false },
		    { display: '录入人员', name: 'recordPerson', isdefault:true }
		],
		showSearch:true,
		height:Height*0.9,
        showcheckbox:true,
        nomsg: '没有符合条件的物业费记录',
        usepager:true,
        useRp:true,
        rp: 15,
		showTableToggleBtn: true
	});
});

function cfImport(){
	openAddWindow('#cfImport');
}

function cfEdit(){
	var rowid,idString="";
	$("#cf_list td input:checked").each(function(){
		rowid=$(this).parent().parent().parent().attr("id");
		rowid=rowid.substr(3);
		idString+=rowid+",";
	});
	if(idString==""){
		alert("请选择要修改的物业费记录");
		return;
	}
	idString=idString.substring(0,idString.length-1);
	$.ajax({
		type: 'POST',
		url: 'pre_check?action=edit&idStr='+idString,
		success: function(data){
			if(data.result=='success'){
				openEditWindow('#cfEdit','selectCondoFee?action=edit&idStr='+idString);
			}
			else{
				alert("您选中的记录有已经缴纳物业费的记录，无法修改");
			}
		}
	});
}

function deleteItem(){
	var cfiId = getQueryString("cfiId");
	if(!confirm("你将删除编号为:"+cfiId+"的项目，及其关联的物业费清单"))return;
	$.ajax({
		type: 'POST',
		url: 'pre_check?action=deleteItem&cfiId='+cfiId,
		success: function(data){
			if(data.result=='success'){
				$.ajax({
					type: 'POST',
					url: 'cf_item_delete?cfiId='+cfiId,
					success: function(data){
						alert("项目删除成功");
						window.parent.location.href='cf_item_list.jsp';
					}
				});
			} else {
				alert("您无法删除该项目,可能的原因：\n(1)该项目已经有物业费记录被小区管理员录入");
			}
		}
	});
}

function deleteList(){
	var rowid,idString="";
	$("#cf_list td input:checked").each(function(){
		rowid=$(this).parent().parent().parent().attr("id");
		rowid=rowid.substr(3);
		idString+=rowid+",";
	});
	if(idString==""){
		alert("请选择要删除的物业费记录");
		return;
	}
	idString=idString.substring(0,idString.length-1);
	if(!confirm("您将删除编号为："+idString+"物业费记录"))return;
	$.ajax({
		type: 'POST',
		url: 'pre_check?action=deleteList&idStr='+idString,
		success: function(data){
			if(data.result=='success'){
				$.ajax({
					type: 'POST',
					url: 'cf_delete?idStr='+idString,
					success: function(data){
						alert("记录删除成功");
						window.location.href='cf_list_by_item.jsp?cfiId='+getQueryString("cfiId");
					}
				});
			} else {
				alert("您选中的记录不能删除,可能有以下原因：\n(1)该记录已经录入实收金额\n(2)该记录已经通过审核");
				return;
			}
		}
	});
}

function cfAudit(){
	var rowid,idString="";
	$("#cf_list td input:checked").each(function(){
		rowid=$(this).parent().parent().parent().attr("id");
		rowid=rowid.substr(3);
		idString+=rowid+",";
	});
	if(idString==""){
		alert("请选择要审核的物业费记录");
		return;
	}
	idString=idString.substring(0,idString.length-1);
	$.ajax({
		type: 'POST',
		url: 'pre_check?action=audit&idStr='+idString,
		success: function(data){
			if(data.result=='success'){
				openEditWindow('#cfAudit','selectCondoFee?action=audit&idStr='+idString);
			}
			else{
				alert("您选中的记录有无法审核,可能有以下原因：\n(1)该记录已经通过审核(2)该记录尚未录入实收金额");
			}
		}
	});
}
