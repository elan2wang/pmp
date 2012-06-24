/**
 * author: Elan Wang
 * email： shohokh@gmail.com
 * create:   2012-6-22
 * 
 * this script is used by the ef_page.jsp
 */ 
$(function(){
	$(".content .innercontent").eq(0).show();
	document.getElementById("ef_item_add").src="ef_item_add.jsp";
	
	$("#tab1").click(function(){
	    document.getElementById("ef_item_add").src="ef_item_add.jsp";
		$(".nav li").removeClass("active");	
		$(this).addClass('active');
		$(".content .innercontent").hide().eq(0).show();
		return false;
	});
	$("#tab2").click(function(){
	    document.getElementById("ef_house_list").src="ef_house_list.jsp";
		$(".nav li").removeClass("active");	
		$(this).addClass('active');
		$(".content .innercontent").hide().eq(1).show();
		return false;
	});
	
	$("#tab3").click(function(){
	    document.getElementById("ef_item_list").src="ef_item_list.jsp";
		$(".nav li").removeClass("active");	
		$(this).addClass('active');
		$(".content .innercontent").hide().eq(2).show();
		return false;
	});
});