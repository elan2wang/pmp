// JavaScript Document

$(function(){
	$(".content .innercontent").eq(0).show();

	$("#tab1").click(function(){
		$(".nav li").removeClass("active");	
		$(this).addClass('active');
		$(".content .innercontent").hide().eq(0).show();
		return false;
	});
    $('#cmpylist').flexigrid({
    	url:"loadCompanyList",
    	dataType:"json",
    	colModel: [
    	    { display: '公司名称',name:'comName', width: Width*0.15, align: 'center' },
    	    { display: '注册日期', name:'registerTime',width: Width*0.1, align: 'center' },
    	    { display: '公司法人',name:'comLegal', width: Width*0.06,align: 'center' },
    	    { display: '联系方式',name:'comPhone', width: Width*0.1, align: 'center' },
    	    { display: '工商执照',name:'comLicense', width: Width*0.1, align: 'center' },
    	    { display: '注册资金(万)',name:'registerMoney', width: Width*0.06, align: 'center' },
    	    { display: '公司地址',name:'comAddress', width: Width*0.2, align: 'left'},
    	    { display: '备注',name:'comDesc', width: Width*0.2, align: 'center' ,hide:'true'}
    	],
        buttons : [
            {name: '添加新公司', bclass: 'add', onpress : addCompany},
		    {separator: true}
	    ],
	    searchitems:[
	        { display: '公司名称', name: 'comName', isDefault:true },
	    ],
        height:Height*0.82,
        showcheckbox:true,
        showSearch:true,
        usepager: true,
	    useRp: true,
	    rp: 15,
	    operation:true,
	    operationcontent:'<a href="javascript:void(0)" onclick="openEditCmpy($(this).parent().parent().parent())">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"#\" onclick=\"deleteCmpy($(this).parent().parent().parent(),$(this).parent().parent().parent());\">删除</a>',
	    operationWidth: Width*0.15
	});
});

function addCompany(){
	$('#newCmpy').window('open');
}

function openEditCmpy(obj){
	var id=parseInt(obj.attr("id").substr(3));
	var url = 'getCompany?comid='+id;
	openEditWindow("#editCmpy",url);
}

function deleteCmpy(obj,objid){
	var id = parseInt(objid.attr("id").substr(3));
	if(!confirm("您将删除该公司及该公司所有的信息,确定删除吗?"))return;
	$.ajax({
        type: "POST",
        url: 'deleteCompany?comid='+id,
        dataType: "json",
        success : function(data){
        	alert(data.msg);
        	window.location.href = "cmpy_list.jsp";
        }
	});
}			
		
