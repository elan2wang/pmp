/**
 * author: ChrussyGe
 * email： chrussyge@gmail.com
 * create:   2012-4-17
 * 
 * this script is used by the smsc_list.jsp
 */
$(function(){
	$(".content .innercontent").eq(0).show();

	$("#tab1").click(function(){
			$(".nav li").removeClass("active");	
			$(this).addClass('active');
			$(".content .innercontent").hide().eq(0).show();
			return false;
		});
	  $('#smsclist').flexigrid({
		  	 url:"loadSMSCompanyList",
		     dataType:"json",
		     type: "POST",
		     colModel: [
             { display: '信息机名称',name:'smscName', width: Width*0.1, align: 'center' },
			 { display: '信息机上行地址',name:'smsUpUrl', width: Width*0.25, align: 'center' },
             { display: '信息及下行地址',name:'smsDownUrl', width: Width*0.25,align: 'center' },
			 { display: '应用账号', name:'username',width: Width*0.05, align: 'center' },
			 { display: '应用扩展码',name:'extendCode', width: Width*0.05, align: 'center' },
			 { display: '公司名称',name:'company', width: Width*0.1, align: 'center' },
             ],
             buttons : [
    			       	{name: '添加新项目', bclass: 'add', onpress : openAddSMSC},
    			     	{name: '删除选中条目', bclass: 'delete', onpress : deleteSMSCs},
    			       	{separator: true}
    		],
    		searchitems:[
    		 		    { display: '信息机名称', name: '', isDefault:true },
    		 		],
    		 height:Height*0.79,
             showcheckbox:true,
             usepager: true,
     		 useRp: true,
     		 rp: 15,
     		 operation:true,
     		 operationcontent:'<a href="javascript:void(0)" onclick="openEditSMSC($(this).parent().parent().parent())">编辑</a>',
			 operationWidth: Width*0.05       
	  });
	});

function smscImport(){
	openAddWindow('#smscImport');
}

function openAddSMSC(){
	
	$('#newSmsc').window({
        top:10,   
        left:($(window).width() - 450) * 0.5
	 });
    $('#newSmsc').window('open');
//    loadCompanyList();
}
function closeAddSMSC(){
	//alert("close");
    $('#newSmsc').window('close');
}
function deleteSMSC(obj,objid)
{
	var id = parseInt(objid.attr("id").substr(3));
	if(!confirm("确定删除？"))
	{
		return;
	}
	$.ajax({
		type: "POST",
		url: "deleteSMSCompany?smscId="+id,
	    dataType: "json",
	    success : function(data){
	    	obj.hide();
	    }
    });
}
function deleteSMSCs()
{
	var rowid,idString="";
	$("#smsclist td input:checked").each(function(){
		rowid=$(this).parent().parent().parent().attr("id");
		rowid=rowid.substr(3);
		idString+=rowid+",";
	});
	if(idString==""){
		alert("请选择删除的信息机记录");
		return;
	}
	idString=idString.substring(0,idString.length-1);
	if(!confirm("您将删除编号为："+idString+"的信息机"))return;
	$.ajax({
		type: "POST",
		url: "batchDeleteSMSCompany?deleteIdString="+idString,
	    dataType: "json",
	    success : function(data){
	    }
    });
	$('#smsclist').flexReload();
}
var windowsOpened=false;
function openEditSMSC(obj){
	var id=parseInt(obj.attr("id").substr(3));
	var url = 'getSMSCompany?smscId='+id;
	openEditWindow("#editSmsc",url);
    windowsOpened = true;
}
function closeEditSMSC(){
    $('#editSmsc').window('close');
}
