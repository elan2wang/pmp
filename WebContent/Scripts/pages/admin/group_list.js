// JavaScript Document
$(function(){
	$(".content .innercontent").eq(0).show();
	$("#tab1").click(function(){
		$(".nav li").removeClass("active");	
		$(this).addClass('active');
		$(".content .innercontent").hide().eq(0).show();
		return false;
	});
	$('#grouplist').flexigrid({
		colModel: [
            { display: '序号',  width: 40,  align: 'center' },
            { display: '用户组名称', width: 200, align: 'center' },
            { display: '用户组描述', width: 200, align: 'center' },
            { display: '用户组级别', width: 100,align: 'center' },
            { display: '用户组关联域', width: 200,align: 'center' },
            { display: '操作',  width: 220, sortable: true, align: 'center', align: 'center' }
	    ],
	    height:305
	});  
});