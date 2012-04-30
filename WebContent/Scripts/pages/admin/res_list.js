// JavaScript Document
$(function(){
	$(".content .innercontent").eq(0).show();
	$("#tab1").click(function(){
		$(".nav li").removeClass("active");	
		$(this).addClass('active');
		$(".content .innercontent").hide().eq(0).show();
		return false;
	});
	$('#reslist').flexigrid({
		colModel: [
            { display: '序号',  width: 40,  align: 'center' },
            { display: '资源名称', width: 100, align: 'center' },
            { display: '资源类型', width: 100, align: 'center' },
            { display: '资源链接', width: 100,align: 'center' },
            { display: '资源描述', width: 200,align: 'center' },
            { display: '是否可用', width: 100,align: 'center' },
            { display: '是否为系统权限', width: 200, align: 'center' },
            { display: '操作',  width: 220, sortable: true, align: 'center', align: 'center' }
        ],
        height:305
	});
});