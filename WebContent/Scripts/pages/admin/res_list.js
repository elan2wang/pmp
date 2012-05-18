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
             { display: '序号',  width: Width*0.02,  align: 'center' },
             { display: '资源名称', width: Width*0.12, align: 'center' },
             { display: '资源类型', width: Width*0.1, align: 'center' },
             { display: '是否为系统权限', width: Width*0.1, align: 'center' },
             { display: '是否可用', width: Width*0.05,align: 'center' },
			 { display: '资源链接', width: Width*0.2,align: 'center' },
			 { display: '资源描述', width: Width*0.25,align: 'center' },
             { display: '操作',  width: Width*0.1, align: 'center' }
        ],
        height:Height*0.93
	});
});