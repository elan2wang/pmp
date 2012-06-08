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
             { display: '序号',  width: Width*0.05,  align: 'center' },
             { display: '用户组名称', width: Width*0.2, align: 'left' },
			 { display: '关联域', width: Width*0.15, align: 'left' },
			 { display: '用户组级别', width: Width*0.1,align: 'center' },
			 { display: '用户组描述', width: Width*0.3,align: 'left' },
             { display: '操作',  width: Width*0.1, align: 'center' }
	    ],
	    height:Height*0.95
	});  
});