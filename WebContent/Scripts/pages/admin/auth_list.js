// JavaScript Document
$(function(){
	$(".content .innercontent").eq(0).show();
	$("#tab1").click(function(){
		$(".nav li").removeClass("active");	
		$(this).addClass('active');
		$(".content .innercontent").hide().eq(0).show();
		return false;
	});
	$('#authlist').flexigrid({
		colModel: [
            { display: '序号',  width: 40,  align: 'center' },
            { display: '权限名称', width: 200, align: 'center' },
            { display: '权限描述', width: 200, align: 'center' },
            { display: '是否可用', width: 200,align: 'center' },
            { display: '是否管理权限', width: 200, align: 'center' },
            { display: '操作',  width: 220, sortable: true, align: 'center', align: 'center' }
	    ],
	    height:305
	});
});