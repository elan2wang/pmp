// JavaScript Document
$(function(){
	$(".content .innercontent").eq(0).show();
	$("#tab1").click(function(){
		$(".nav li").removeClass("active");	
		$(this).addClass('active');
		$(".content .innercontent").hide().eq(0).show();
		return false;
	});
    $('#rolelist').flexigrid({
    	colModel: [
            { display: '序号',  width: 40,  align: 'center' },
            { display: '角色名称', width: 200, align: 'center' },
            { display: '角色描述', width: 200, align: 'center' },
            { display: '是否可用', width: 200,align: 'center' },
            { display: '是否管理角色', width: 200, align: 'center' },
            { display: '操作',  width: 220, sortable: true, align: 'center', align: 'center' }
    	],
    	height:305
    });
});