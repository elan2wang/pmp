/**
 * author: Elan
 * email： shohokh@gmail.com
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
                 { display: '账号名称',name:'smscName', width: Width*0.13, align: 'center' },
                 { display: '信息机上行地址',name:'smsUpUrl', width: Width*0.25, align: 'center' },
                 { display: '信息及下行地址',name:'smsDownUrl', width: Width*0.25,align: 'center' },
                 { display: '应用账号', name:'username',width: Width*0.06, align: 'center' },
                 { display: '扩展码',name:'extendCode', width: Width*0.05, align: 'center' },
                 { display: '公司名称',name:'company.comName', width: Width*0.13, align: 'center' },
             ],
             buttons : [
    		     {name: '添加账号', bclass: 'add', onpress : addSMSC},
    		     {separator: true},
    			 {name: '删除账号', bclass: 'delete', onpress : deleteSMSCs}
    		 ],
    		 height:Height*0.88,
             showcheckbox:true,
             usepager: true,
     		 useRp: true,
     		 rp: 15,
     		 operation:true,
     		 operationcontent:'<a href="javascript:void(0)" onclick="openEditSMSC($(this).parent().parent().parent())">编辑</a>',
			 operationWidth: Width*0.06       
	  });
	});

function addSMSC(){
	$('#newSmsc').window({
	    top:10,   
	    left:($(window).width() - 450) * 0.5
	 });
	$('#newSmsc').window('open');
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
		url: "batchDeleteSMSCompany?idStr="+idString,
	    dataType: "json",
	    success : function(data){
	    	alert("删除成功");
	    	window.location.href = "smsc_list.jsp";
	    }
    });
}

function openEditSMSC(obj){
	var id=parseInt(obj.attr("id").substr(3));
	var url = 'getSMSCompany?smscId='+id;
	openEditWindow("#editSmsc",url);
}