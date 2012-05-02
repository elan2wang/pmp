/**
 * author: Elan Wang
 * email： shohokh@gmail.com
 * create:   2012-4-19
 * 
 * this script is used by the mod_list.jsp
 */ 
$(function(){
	$(".content .innercontent").eq(0).show();
	$('#module_list').flexigrid({
		colModel:
			[{ display:'序号', width: Width*0.02, align:'center' },
			 { display:'模块名称', width: Width*0.15, align:'center' },
			 { display:'模块级别', width: Width*0.1, align:'center' },
			 { display:'是否启用', width: Width*0.1, align:'center' },
			 { display:'管理模块', width: Width*0.1, align:'center' },
			 { display:'模块链接', width: Width*0.42, align:'center' },
			 { display:'操作', width: Width*0.1, align:'center' }
			],
		height:Height
	});
});