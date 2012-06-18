/**
 * author: Elan Wang
 * email： shohokh@gmail.com
 * create:   2012-5-13
 * 
 * this script is used by the cf_page.jsp
 */ 
$(function(){
	$(".content .innercontent").eq(0).show();
	document.getElementById("cf_month_list").src="cf_month_list.jsp";
	
	$("#tab1").click(function(){
	    document.getElementById("cf_month_list").src="cf_month_list.jsp";
		$(".nav li").removeClass("active");	
		$(this).addClass('active');
		$(".content .innercontent").hide().eq(0).show();
		return false;
	});
	$("#tab2").click(function(){
	    document.getElementById("cf_item_add").src="cf_item_add.jsp";
		$(".nav li").removeClass("active");	
		$(this).addClass('active');
		$(".content .innercontent").hide().eq(1).show();
		return false;
	});
	$("#tab3").click(function(){
	    document.getElementById("cf_house_list").src="cf_house_list.jsp";
		$(".nav li").removeClass("active");	
		$(this).addClass('active');
		$(".content .innercontent").hide().eq(2).show();
		return false;
	});
	$("#tab4").click(function(){
	    document.getElementById("cf_item_list").src="cf_item_list.jsp";
		$(".nav li").removeClass("active");	
		$(this).addClass('active');
		$(".content .innercontent").hide().eq(3).show();
		return false;
	});
});