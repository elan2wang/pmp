$(function(){
	var cfiId = getQueryString("cfiId");
	/* set the value of the hidden input */
	document.getElementById("cfiId").value = cfiId;
	
	$('#cf_list').flexigrid({
		url:"loadCondoFeeList_ByCFI?cfiId="+cfiId,
		dataType:"json",
        colModel:[
            { display: '房号', name: 'h.House_Num', width: Width*0.1, sortable:true, align: 'center' },
            { display: '业主', name: 'Owner_Name', width: Width*0.1, sortable:true, align: 'center' },
            { display: '年份', name: 'CF_Year', width: Width*0.05, sortable:true, align: 'center' },
            { display: '月份', name: 'CF_Month', width: Width*0.05, sortable:true, align: 'center' },
            { display: '状态', name: 'State', width: Width*0.08, sortable:true, align: 'center' },
            { display: '应收金额', name: 'Ought_Money', width: Width*0.06, sortable:true, align: 'center' },
            { display: '实收金额', name: 'Fetch_Money', width: Width*0.06, sortable:true, align: 'center' },
            { display: '录入时间', name: 'Input_Time', width: Width*0.1, sortable:true, align: 'center' },
            { display: '录入人员', name: 'Record_Person', width: Width*0.1, sortable:true, align: 'center' },
            { display: '备注', name: 'Comment', width: Width*0.15, sortable:true, align: 'center' }
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
			{ display: '业主', name: 'Owner_Name', isdefault:false },
			{ display: '房号', name: 'h.House_Num', isdefault:false },
		    { display: '年份', name: 'CF_Year', isdefault:false },
		    { display: '月份', name: 'Cf_Month', isdefault:false },
		    { display: '状态', name: 'State', isdefault:true },
		    { display: '录入时间', name: 'Input_Time', isdefault:false },
		    { display: '录入人员', name: 'Record_Person', isdefault:false }
		],
		searchQueryStrs:[
           {selectName:'qtype1',queryStrName:'query1'},
           {selectName:'qtype2',queryStrName:'query2'}
    	],
    	title:true,
		showSearch:true,
		height:Height*0.83,
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
		dataType: "json",
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
		dataType: "json",
		success: function(data){
			if(data.result=='success'){
				$.ajax({
					type: 'POST',
					url: 'cf_item_delete?cfiId='+cfiId,
					dataType: "json",
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
		dataType: "json",
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
		dataType: "json",
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

function selectAllOrNone(the){
	var checks=$(the).parent().parent().parent().find(":checkbox");
	if($(the).attr("checked")){
		checks.each(function(){
			$(this).attr("checked",true);
		});
	}
	else{
		checks.each(function(){
			$(this).attr("checked",false);
		});
	}	
}
