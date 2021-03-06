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
             { display: '序号',  width: Width*0.02,  align: 'center' },
             { display: '角色名称', width: Width*0.15, align: 'center' },
             { display: '角色层次', width: Width*0.05, align: 'center' },
             { display: '是否可用', width: Width*0.1,align: 'center' },
			 { display: '是否管理角色', width: Width*0.1, align: 'center' },
			 { display: '角色描述', width: Width*0.3, align: 'left' },
             { display: '操作',  width: Width*0.24,align: 'center' }
    	],
    	height:Height*0.95
    });
});