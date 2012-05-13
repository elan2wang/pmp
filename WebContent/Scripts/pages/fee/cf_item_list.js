/**
 * author: Elan Wang
 * email： shohokh@gmail.com
 * create:   2012-5-10
 * 
 * this script is used by the cf_item_list.jsp
 */ 

$(function(){
	$(".content .innercontent").eq(0).show();
	$('#cfi_list').flexigrid({
	    colModel: [
	        { display: '序号',  width: 20,  align: 'center' },
	        { display: '项目名称', width:280, align: 'center' },
	        { display: '操作',  width: 150, align: 'center' }
	    ],
	    height: 360
	});
});

function openList(dest,url){
	document.getElementById(dest).src=url;
	$("#right_main").css("display","block");
}
